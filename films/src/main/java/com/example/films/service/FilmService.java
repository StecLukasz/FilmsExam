package com.example.films.service;

import com.example.films.HttpClient.OMDb;
import com.example.films.model.Film;
import com.example.films.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    @Autowired
    private FilmRepository repository;
    @Autowired
    private OMDb omDb;

    public List<Film> searchedFilms(String title, int page, int pageSize) throws Exception {
        List<Film> filmsFromOMDb = omDb.getAllFilmsFromOMDb(title, page, pageSize);
        return filmsFromOMDb;
    }

    public List<Film> getDetailsForFilms(String title, int page, int pageSize) throws Exception {
        List<Film> filmsFromOMDb = omDb.getAllFilmsFromOMDb(title, page, pageSize);

        for (Film film : filmsFromOMDb) {
            Film detailedFilm = omDb.getFilmDetails(film.getId());
            if (detailedFilm != null) {
                film.setTitle(detailedFilm.getTitle());
                film.setPlot(detailedFilm.getPlot());
                film.setGenre(detailedFilm.getGenre());
                film.setDirector(detailedFilm.getDirector());
                film.setPosterUrl(detailedFilm.getPosterUrl());
            }
        }
        repository.saveAll(filmsFromOMDb);
        return filmsFromOMDb;
    }

    public void addToFavorites(String filmId) {
        Optional<Film> existingFilm = repository.findById(Long.valueOf(filmId));
        if (existingFilm.isPresent()) {
            Film film = existingFilm.get();
            Film detailedFilm = omDb.getFilmDetails(filmId);

            if (detailedFilm != null) {
                film.setTitle(detailedFilm.getTitle());
                film.setPlot(detailedFilm.getPlot());
                film.setGenre(detailedFilm.getGenre());
                film.setDirector(detailedFilm.getDirector());
                film.setPosterUrl(detailedFilm.getPosterUrl());
            }
            film.setFavorite(true);
            repository.save(film);
        } else {
            Film newFilm = omDb.getFilmDetails(filmId);

            if (newFilm != null) {
                newFilm.setFavorite(true);
                repository.save(newFilm);
            }
        }
    }

    public List<Film> getFavoriteFilms() {
        return repository.findByFavoriteTrue();
    }

}
