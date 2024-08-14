package com.example.demo.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class BookingFilmResponse {

    private Long id;

    private String room;

    private List<String> startTime;

}
