package com.iotgqldemo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iotgqldemo.service.GraphQLService;

import graphql.ExecutionResult;

@RequestMapping("/films")
@RestController
public class FilmResource {

    @Autowired
    GraphQLService graphQLService;

    @PostMapping
    public ResponseEntity<Object> getAllFilms(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
