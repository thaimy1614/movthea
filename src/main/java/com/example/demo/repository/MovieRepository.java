package com.example.demo.repository;

import com.example.demo.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByStatus(Movie.MovieStatus status);

    List<Movie> findAllByTitleContainingOrMainActorContainingOrMainActressContaining(String title, String actor, String actress);
}
