package com.iotgqldemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotgqldemo.model.Film;
import com.iotgqldemo.repository.FilmRepository;

import graphql.schema.DataFetcher;

@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public DataFetcher getFilm() {
        return dataFetchingEnvironment -> {
            int id = dataFetchingEnvironment.getArgument("id");
            return filmRepository.findById(id);
        };
	}
	
	public DataFetcher getFilms() {
        return dataFetchingEnvironment -> {
            return filmRepository.findAll();
        };
	}
	
	public DataFetcher createFilm() {
        return dataFetchingEnvironment -> {
        	Object rawInput = dataFetchingEnvironment.getArgument("film");
            Film film = objectMapper.convertValue(rawInput, Film.class);
            
            return filmRepository.save(film);
        };
	}
	
	public <T> DataFetcher updateFilm() {
        return dataFetchingEnvironment -> {
            int id = dataFetchingEnvironment.getArgument("id");
        	Object rawInput = dataFetchingEnvironment.getArgument("film");
        	
            Film film = filmRepository.findById(id).get();
            film = objectMapper.convertValue(rawInput, Film.class);
            film.setId(id);
            
            return filmRepository.save(film);
        };
	}
	
	public DataFetcher deleteFilm() {
        return dataFetchingEnvironment -> {
            int id = dataFetchingEnvironment.getArgument("id");
            
            try {
            	filmRepository.deleteById(id);
            	return true;
            	
            }catch(Exception e) {
            	return false;
            }
        };
	}
}
