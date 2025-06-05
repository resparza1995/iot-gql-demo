package com.iotgqldemo.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.iotgqldemo.model.Film;
import com.iotgqldemo.repository.FilmRepository;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

    @Autowired
    FilmService filmService;
	
    @Autowired
    FilmRepository filmRepository;

    @Value("classpath:films.graphql")
    Resource resource;

    private GraphQL graphQL;
    
    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {

        //Cargar unas peliculas para la demo
        loadFilms();

        // Obtener el schema
        File schemaFile = resource.getFile();
        
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadFilms() {
    	Film film = new Film(1, "La leyenda del Zorro", "Sin descripcion", 2005, 7);
    	Film film2 = new Film(2, "La Máscara del Zorro", "Sin descripcion", 1998, 3);

    	filmRepository.save(film);
    	filmRepository.save(film2);
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                		.dataFetcher("allFilms", filmService.getFilms())
                		.dataFetcher("film", filmService.getFilm()))
                .type("Mutation", typeWiring -> typeWiring
                		.dataFetcher("createFilm", filmService.createFilm())
                		.dataFetcher("updateFilm", filmService.updateFilm())    
                		.dataFetcher("deleteFilm", filmService.deleteFilm())    
                ).build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
