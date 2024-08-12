package com.example.demo.service;

import com.example.demo.model.entity.*;
import com.example.demo.model.request.AddFilmRequest;
import com.example.demo.model.request.AddRoomRequest;
import com.example.demo.model.response.RoomResponse;

import java.util.List;

public interface ManagerService {
    AddFilmRequest addFilm(AddFilmRequest filmEntity);

    Boolean deleteFilm(Long id);

    RoomEntity room(AddRoomRequest roomEntity);

    Boolean deleteRoom(Long id);

    UserEntity user(UserEntity userEntity);

    Boolean deleteUser(Long id);

    List<UserEntity> getAllEmployee();

    Integer getAllTicket();

    List<FilmEntity> getAllFilm();

    List<RoomResponse> getAllRoom();

    List<LocationEntity> getAllLocation();
}
