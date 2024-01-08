package com.example.films.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
public class Film {
    @JsonProperty("imdbID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Poster")
    private String posterUrl;
}
