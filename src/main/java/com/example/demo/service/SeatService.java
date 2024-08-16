package com.example.demo.service;

import com.example.demo.model.entity.Seat;

import java.util.List;

public interface SeatService {
    public boolean areSeatsAvailable(List<Seat> seatList, String[] seatCodes);
}
