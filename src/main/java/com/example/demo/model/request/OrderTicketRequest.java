package com.example.demo.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderTicketRequest {
    private Long userId;
    private String startTime;
    private Long roomId;
    private List<String> seats;
    private boolean payment = false;
}
