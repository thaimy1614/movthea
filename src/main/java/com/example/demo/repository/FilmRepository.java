package com.example.demo.repository;

import com.example.demo.model.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    List<FilmEntity> getAllByStatusIs(boolean status);
}
