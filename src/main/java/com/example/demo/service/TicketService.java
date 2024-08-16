package com.example.demo.service;

import com.example.demo.model.entity.Ticket;
import com.example.demo.model.entity.UserEntity;

import java.util.List;

public interface TicketService {
    void addTicket(Ticket ticket);

    Ticket getTicket(Long id);

    List<Ticket> getAllTicketsByUsername(String username);

    List<Ticket> getAllTickets();

    void reject(Long id);

    void confirm(Long id);
}
