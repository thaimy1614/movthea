package com.example.demo.service.impl;

import com.example.demo.model.entity.Payment;
import com.example.demo.model.entity.Ticket;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public void addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public List<Ticket> getAllTicketsByUsername(String username){
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            return List.of();
        }
        return ticketRepository.findAllByUser(user.get());
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void reject(Long id) {
        Ticket ticket = getTicket(id);
        ticket.setStatus(Ticket.Status.CANCELLED);
    }

    @Override
    public void confirm(Long id) {
        Ticket ticket = getTicket(id);
        ticket.setStatus(Ticket.Status.CONFIRMED);
        Payment payment = Payment.builder()
                .ticket(ticket)
                .price(ticket.getTotalPrice())
                .paymentDate(LocalDate.now())
                .build();
    }
}
