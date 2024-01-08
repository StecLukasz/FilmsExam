package com.example.films.HttpClient;

import com.example.films.model.Film;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OMDbResponse {
    @JsonProperty("Search")
    private Film[] search;

    @JsonProperty("totalResults")
    private String totalResults;

    @JsonProperty("Response")
    private String response;

    public Film[] getSearch() {
        return search;
    }

    public void setSearch(Film[] search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

