package com.example.demo.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SeatBookingResponse {
    private String filmName;
    private String time;
    private List<SeatsResponse> seats;
}
