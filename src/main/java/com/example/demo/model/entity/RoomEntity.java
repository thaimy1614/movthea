package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "max_seats")
    private int maxSeats;

    @Column(name = "empty_seats")
    private String emptySeats;

    @Column(name = "selected_seat")
    private String selectedSeat;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Seat> seat;
}