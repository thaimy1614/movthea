package com.example.demo.service;

import com.example.demo.model.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies(Movie.MovieStatus movieStatus);

    boolean addMovie(Movie movie);

    void updateMovie(Movie movie);

    List<Movie> searchMovie(String keyword);

    void deleteMovie(Long id);
}
