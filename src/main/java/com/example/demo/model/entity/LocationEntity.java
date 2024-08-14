package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "location")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location")
    private String location;

    @OneToMany
    @JoinColumn(name = "room")
    private List<RoomEntity> room;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule")
    private List<ScheduleFilmEntity> schedule;
}
