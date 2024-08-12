package com.example.demo.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Location {
    private String location;
    private List<LocaTionResponse> schedule;
}
