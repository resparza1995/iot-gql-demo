package com.iotgqldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iotgqldemo.model.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
