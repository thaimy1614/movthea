package com.example.demo.service.impl;

import com.example.demo.model.entity.RoomEntity;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public List<RoomEntity> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public boolean addRoomEntity(RoomEntity room) {
        try {
            roomRepository.save(room);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateRoomEntity(RoomEntity room) {

    }

    @Override
    public List<RoomEntity> searchRoomEntity(String keyword) {
        return List.of();
    }

    @Override
    public void deleteRoomEntity(Long id) {
        roomRepository.deleteById(id);
    }

    public Optional<RoomEntity> getRoomEntity(Long id) {
        return roomRepository.findById(id);
    }
}
