package com.example.demo.service;

import com.example.demo.model.entity.TicketEntity;
import com.example.demo.model.response.TicketResponse;

import java.util.List;

public interface EmployeeService {
    TicketResponse findTicketById(Long id);

    Boolean checkPayment(Long id);

    TicketResponse paymentTicket(Long id);

    List<TicketResponse> getAllTicket();
}
