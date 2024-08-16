package com.example.demo.repository;

import com.example.demo.model.entity.Ticket;
import com.example.demo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByUser(UserEntity user);
}
