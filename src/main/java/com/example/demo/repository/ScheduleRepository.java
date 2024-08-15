package com.example.demo.repository;

import com.example.demo.model.entity.Movie;
import com.example.demo.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByMovieAndStartAtAfterOrderByStartAtAsc(Movie movie, LocalDate start);
    List<Schedule> findAllByStartAtAfterOrderByStartAtAsc(LocalDate start);
}
