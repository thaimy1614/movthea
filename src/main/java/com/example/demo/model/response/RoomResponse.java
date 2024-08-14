package com.example.demo.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomResponse {
    private Long id;

    private String name;

    private int maxSeats;

    private String emptySeats;

    private String selectedSeat;

    private String location;
}
