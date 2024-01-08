package com.example.films.repository;

import com.example.films.model.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepository extends CrudRepository<Film, Long> {

    List<Film> findAll();

}
