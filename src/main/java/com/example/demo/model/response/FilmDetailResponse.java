package com.example.demo.model.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmDetailResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "filmType")
    private String filmType;

    @Column(name = "age_limit")
    private int ageLimit;

    @Column(name = "image")
    private String image;

    @Column(name = "date_of_publication")
    private String dateOfpublication;

    @Column(name = "price")
    private int price;

    @Column(name = "time")
    private String time;

    @Column(name = "link")
    private String link;

    @Column(name = "language")
    private String language;

    @Column(name = "introduce")
    private String introduce;


    @Column(name = "imageInFilm")
    private String imageInFilm;

    @Column(name = "actor")
    private String actor;

    @Column(name = "crew")
    private String crew;


}
