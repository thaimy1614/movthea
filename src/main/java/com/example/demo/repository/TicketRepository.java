package com.example.demo.repository;

import com.example.demo.model.entity.FilmEntity;
import com.example.demo.model.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> findAllByPayment(boolean payment);

    List<TicketEntity> findAllByFilm(FilmEntity filmEntity);
}
