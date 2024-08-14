package com.example.demo.service;


import com.example.demo.model.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<Schedule> getAllSchedules();

    boolean addSchedule(Schedule movie);

    void updateSchedule(Schedule movie);

    List<Schedule> searchSchedule(String keyword);

    void deleteSchedule(Long id);

    Optional<Schedule> getSchedule(Long id);
}
