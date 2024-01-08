package com.example.films.controller;

import com.example.films.HttpClient.OMDb;
import com.example.films.model.Film;
import com.example.films.repository.FilmRepository;
import com.example.films.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FilmController {
    @Autowired
    private FilmRepository repository;
    @Autowired
    private FilmService service;
    @Autowired
    private OMDb ser;

    @GetMapping("/films")
    public List<Film> getAllFilms(@RequestParam String title,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int pageSize) throws Exception {
        return service.searchedFilms(title, page, pageSize);
    }

    @GetMapping("/search")
    public List<Film> getDetails(@RequestParam String title,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int pageSize) throws Exception {
        return service.getDetailsForFilms(title, page, pageSize);
    }

}
