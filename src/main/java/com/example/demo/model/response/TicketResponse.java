package com.example.demo.model.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TicketResponse {
    private Long id;
    private String price;
    private boolean payment;
    private String timeStart;
    private String customerName;
    private Long orderDate;
    private String film;
    private String room;
    private String seatsNumber;
}
