package com.example.demo.service;

import com.example.demo.model.entity.RoomEntity;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<RoomEntity> getAllRooms();

    boolean addRoomEntity(RoomEntity room);

    void updateRoomEntity(RoomEntity room);

    List<RoomEntity> searchRoomEntity(String keyword);

    void deleteRoomEntity(Long id);

    Optional<RoomEntity> getRoomEntity(Long id);
}
