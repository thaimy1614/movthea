package com.example.demo.repository;

import com.example.demo.model.entity.FilmEntity;
import com.example.demo.model.entity.RoomEntity;
import com.example.demo.model.entity.ScheduleFilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleFilmEntity, Long> {

    List<ScheduleFilmEntity> findAllByFilmEntity(FilmEntity filmEntity);

    ScheduleFilmEntity findAllByRoomEntityAndStartTime(RoomEntity roomEntity, String startTime);
}
