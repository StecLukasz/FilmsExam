package com.example.films.controller;

import com.example.films.HttpClient.OMDb;
import com.example.films.model.Film;
import com.example.films.repository.FilmRepository;
import com.example.films.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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

//    @PostMapping("/favorite/{filmId}")
//    public ResponseEntity<String> addToFavorites(@PathVariable String filmId) {
//    return service.addToFavorites(filmId);
//    }

    @GetMapping("/favorites")
    public List<Film> getFavoriteFilms() {
        return service.getFavoriteFilms();
    }
}
