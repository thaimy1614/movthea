package com.example.demo.service.impl;

import com.example.demo.model.entity.Seat;
import com.example.demo.repository.SeatRepository;
import com.example.demo.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;

    public List<Seat> areSeatsAvailable(List<Seat> seatList, String[] seatCodes) {
        List<Seat> ids = new ArrayList<>();
        Map<String, Seat> seatMap = seatList.stream()
                .collect(Collectors.toMap(Seat::getSeatCode, Function.identity()));

        for (String seatCode : seatCodes) {
            Seat seat = seatMap.get(seatCode);
            if (seat == null || Seat.Status.UNAVAILABLE == seat.getStatus()) {
                return null;
            }
            ids.add(seat);
        }
        return ids;
    }

    public void disableSeats(List<Seat> seatIds) {
        seatIds.forEach(seat -> {
            seat.setStatus(Seat.Status.UNAVAILABLE);
        });
    }
}
