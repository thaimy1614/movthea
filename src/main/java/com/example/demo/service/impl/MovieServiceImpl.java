package com.example.demo.service.impl;

import com.example.demo.model.entity.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies(Movie.MovieStatus movieStatus) {
        return List.of();
    }

    @Override
    public boolean addMovie(Movie movie) {
        try {
            movieRepository.save(movie);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateMovie(Movie movie) {

    }

    @Override
    public List<Movie> searchMovie(String keyword) {
        return List.of();
    }

    @Override
    public void deleteMovie(Long id) {

    }
}
