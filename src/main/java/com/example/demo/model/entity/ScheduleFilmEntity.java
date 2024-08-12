package com.example.demo.model.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "schedule")
public class ScheduleFilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film")
    private FilmEntity filmEntity;

    @ManyToOne
    @JoinColumn(name = "room")
    private RoomEntity roomEntity;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location")
    private LocationEntity location;

    @Column(name = "date")
    private Long date;

    @Column(name = "startTime")
    private String startTime;
}
