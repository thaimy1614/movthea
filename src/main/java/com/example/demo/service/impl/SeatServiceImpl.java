package com.example.demo.service.impl;

import com.example.demo.model.entity.Seat;
import com.example.demo.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {
    public boolean areSeatsAvailable(List<Seat> seatList, String[] seatCodes) {
        Map<String, Seat> seatMap = seatList.stream()
                .collect(Collectors.toMap(Seat::getSeatCode, Function.identity()));

        for (String seatCode : seatCodes) {
            Seat seat = seatMap.get(seatCode);
            if (seat == null || Seat.Status.UNAVAILABLE == seat.getStatus()) {
                return false;
            }
        }
        return true;
    }
}
