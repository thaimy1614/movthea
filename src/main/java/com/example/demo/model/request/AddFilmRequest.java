package com.example.demo.model.request;

import com.example.demo.model.entity.ActorEntity;
import com.example.demo.model.entity.CrewEntity;
import com.example.demo.model.entity.ImageFilmEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddFilmRequest {
    private Long id;

    private String name;

    private String filmType;

    private int ageLimit;

    private String image;

    private String dateOfpublication;

    private int price;

    private String time;

    private String link;

    private String language;

    private String introduce;

    private String poster;

    private List<ImageFilmEntity> imageInFilm;

    private List<ActorEntity> actor;

    private List<CrewEntity> crew;

    private Long roomId;

    private Long date;

    private Long startTime;

    private Long location;


}
