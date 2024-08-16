package com.example.demo.service;

import com.example.demo.model.entity.Seat;

import java.util.List;

public interface SeatService {
    public List<Seat> areSeatsAvailable(List<Seat> seatList, String[] seatCodes);

    public void disableSeats(List<Seat> seats);
}
