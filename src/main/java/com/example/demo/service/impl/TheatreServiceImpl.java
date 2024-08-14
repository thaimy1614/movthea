package com.example.demo.service.impl;

import com.example.demo.model.entity.Theatre;
import com.example.demo.repository.TheatreRepository;
import com.example.demo.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService {
    private final TheatreRepository theatreRepository;

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    public boolean addTheatre(Theatre theatre) {
        try {
            theatreRepository.save(theatre);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateTheatre(Theatre theatre) {

    }

    @Override
    public List<Theatre> searchTheatre(String keyword) {
        return List.of();
    }

    @Override
    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }

    public Optional<Theatre> getTheatre(Long id) {
        return theatreRepository.findById(id);
    }
}
