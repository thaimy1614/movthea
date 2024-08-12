package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "film")
public class FilmEntity {
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
    private Long dateOfpublication;

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

    @Column(name = "poster")
    private String poster;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_film")
    private List<ImageFilmEntity> imageInFilm;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "actor")
    private List<ActorEntity> actor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "crew")
    private List<CrewEntity> crew;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "startTime")
    private Long startTime;
}
