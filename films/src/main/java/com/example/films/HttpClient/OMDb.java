package com.example.films.HttpClient;

import com.example.films.model.Film;
import com.example.films.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class OMDb {
    @Autowired
    private FilmRepository repository;

    private final String apiKey;

    public OMDb() {
        this.apiKey = "8847db11";
    }

    public URI exchange(String query) {
        String https = "http://www.omdbapi.com/?apikey=" + apiKey + "&";
        return URI.create(https + query);
    }

    public List<Film> getAllFilmsFromOMDb(String title, int page, int pageSize) {
        List<Film> films = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String formattedTitle = title.replace(" ", "+");
        String s = "&s=" + formattedTitle + "&page=" + page + "&pageSize=" + pageSize;
        URI uri = exchange(s);

        OMDbResponse omdbResponse = restTemplate.getForObject(uri, OMDbResponse.class);
        if (omdbResponse != null && omdbResponse.getSearch() != null) {
            films.addAll(Arrays.asList(omdbResponse.getSearch()));
            repository.saveAll(films);
        }

        return films;
    }


    public Film getFilmDetails(String imdbId) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = exchange("&i=" + imdbId);
        Film filmDetailsResponse = restTemplate.getForObject(uri, Film.class);
        if (filmDetailsResponse != null) {
            Film film = new Film();
            film.setId(filmDetailsResponse.getId());
            film.setTitle(filmDetailsResponse.getTitle());
            film.setPlot(filmDetailsResponse.getPlot());
            film.setGenre(filmDetailsResponse.getGenre());
            film.setDirector(filmDetailsResponse.getDirector());
            film.setPosterUrl(filmDetailsResponse.getPosterUrl());
            return film;
        }
        return null;
    }
}

//TODO: dodac search parameter do wyszukiwania filmu po tytule albo po części a później dodać możliwość dodawania tego tutułu do ulubionych