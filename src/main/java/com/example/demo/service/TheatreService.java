package com.example.demo.service;

import com.example.demo.model.entity.Theatre;

import java.util.List;
import java.util.Optional;

public interface TheatreService {
    List<Theatre> getAllTheatres();

    boolean addTheatre(Theatre movie);

    void updateTheatre(Theatre movie);

    List<Theatre> searchTheatre(String keyword);

    void deleteTheatre(Long id);

    Optional<Theatre> getTheatre(Long id);
}
