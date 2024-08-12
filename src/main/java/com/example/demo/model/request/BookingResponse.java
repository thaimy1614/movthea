package com.example.demo.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingResponse {
    private String name;
    private String linkTrailer;
    private String poster;
    private List<Location> location;
}
