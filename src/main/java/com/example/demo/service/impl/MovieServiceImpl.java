package com.example.demo.service.impl;

import com.example.demo.model.entity.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies(Movie.MovieStatus movieStatus) {
        if(movieStatus==null){
            return movieRepository.findAll();
        }
        else{
            return movieRepository.findAllByStatus(movieStatus);
        }
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
        movieRepository.deleteById(id);
    }

    public Optional<Movie> getMovie(Long id){
        return movieRepository.findById(id);
    }
}
