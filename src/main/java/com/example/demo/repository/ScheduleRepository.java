package com.example.demo.repository;

import com.example.demo.model.entity.RoomEntity;
import com.example.demo.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
