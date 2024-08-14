package com.example.demo.service.impl;

import com.example.demo.model.entity.TicketEntity;
import com.example.demo.model.response.TicketResponse;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private TicketRepository ticketRepository;

    @Override
    public TicketResponse findTicketById(Long id) {
        TicketEntity ticketEntity = ticketRepository.getOne(id);
        return ticketResponse(ticketEntity);
    }

    @Override
    public Boolean checkPayment(Long id) {
        return ticketRepository.getOne(id).isPayment();
    }

    @Override
    public TicketResponse paymentTicket(Long id) {
        TicketEntity ticketEntity = ticketRepository.getOne(id);
        ticketEntity.setPayment(true);
        ticketRepository.save(ticketEntity);
        return ticketResponse(ticketEntity);
    }

    @Override
    public List<TicketResponse> getAllTicket() {
        List<TicketResponse> ticketResponses = new ArrayList<>();
        List<TicketEntity> ticketEntities = ticketRepository.findAll();
        for (TicketEntity ticketEntity : ticketEntities) {
            TicketResponse ticketResponse = ticketResponse(ticketEntity);
            ticketResponses.add(ticketResponse);
        }
        return ticketResponses;
    }

    private TicketResponse ticketResponse(TicketEntity ticketEntity) {
        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setPayment(ticketEntity.isPayment());
        ticketResponse.setFilm(ticketEntity.getFilm().getName());
        ticketResponse.setRoom(ticketEntity.getRoom().getName());
        ticketResponse.setCustomerName(ticketEntity.getUser().getName());
        ticketResponse.setPrice(ticketEntity.getPrice());
        ticketResponse.setId(ticketEntity.getId());
        ticketResponse.setSeatsNumber(ticketEntity.getNumberSeats());
        ticketResponse.setTimeStart(ticketEntity.getTimeStart());
        ticketResponse.setOrderDate(ticketEntity.getDate());
        return ticketResponse;
    }
}
