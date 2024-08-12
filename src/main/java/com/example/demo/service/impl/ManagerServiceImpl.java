package com.example.demo.service.impl;

import com.example.demo.exception.LoginFalseException;
import com.example.demo.model.entity.*;
import com.example.demo.model.request.AddFilmRequest;
import com.example.demo.model.request.AddRoomRequest;
import com.example.demo.model.response.RoomResponse;
import com.example.demo.repository.*;
import com.example.demo.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    FilmRepository filmRepository;

    RoomRepository roomRepository;

    UserRepository userRepository;

    TicketRepository ticketRepository;

    LocationRepository locationRepository;

    ScheduleRepository scheduleRepository;

    ActorRepository actorRepository;

    @Override
    public AddFilmRequest addFilm(AddFilmRequest filmEntity) {
        return null;
    }

    @Override
    public Boolean deleteFilm(Long id) {
        return null;
    }

    @Override
    public RoomEntity room(AddRoomRequest roomEntity) {
        return null;
    }

    @Override
    public Boolean deleteRoom(Long id) {
        return null;
    }

    @Override
    public UserEntity user(UserEntity userEntity) {
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        return null;
    }

    @Override
    public List<UserEntity> getAllEmployee() {
        return List.of();
    }

    @Override
    public Integer getAllTicket() {
        return 0;
    }

    @Override
    public List<FilmEntity> getAllFilm() {
        return List.of();
    }

    @Override
    public List<RoomResponse> getAllRoom() {
        return List.of();
    }

    @Override
    public List<LocationEntity> getAllLocation() {
        return List.of();
    }

//    @Override
//    public AddFilmRequest addFilm(AddFilmRequest addFilmRequest) {
//        FilmEntity filmEntity = new FilmEntity();
//        if (addFilmRequest.getId() != null) {
//            filmEntity = filmRepository.getOne(addFilmRequest.getId());
//        }
//                filmEntity.setName(addFilmRequest.getName());
//        filmEntity.setFilmType(addFilmRequest.getFilmType());
//        filmEntity.setAgeLimit(addFilmRequest.getAgeLimit());
//        filmEntity.setImage(addFilmRequest.getImage());
//        filmEntity.setDateOfpublication(addFilmRequest.getDate());
//        filmEntity.setPrice(addFilmRequest.getPrice());
//        filmEntity.setTime(addFilmRequest.getTime());
//        filmEntity.setLink(addFilmRequest.getLink());
//        filmEntity.setLanguage(addFilmRequest.getLanguage());
//        filmEntity.setIntroduce(addFilmRequest.getIntroduce());
//        filmEntity.setPoster(addFilmRequest.getPoster());
//        filmEntity.setImageInFilm(addFilmRequest.getImageInFilm());
//        filmEntity.setStartTime(addFilmRequest.getStartTime());
//        filmEntity.setActor(addFilmRequest.getActor());
//        filmEntity.setCrew(addFilmRequest.getCrew());
//        filmEntity = filmRepository.save(filmEntity);
//        ScheduleFilmEntity scheduleFilmEntity = new ScheduleFilmEntity();
//        if (addFilmRequest.getId() != null) {
//            scheduleFilmEntity = scheduleRepository.findAllByFilmEntity(filmEntity).get(0);
//        }
//        scheduleFilmEntity.setFilmEntity(filmEntity);
//        scheduleFilmEntity.setDate(addFilmRequest.getDate());
//        scheduleFilmEntity.setRoomEntity(roomRepository.getOne(addFilmRequest.getRoomId()));
//        scheduleFilmEntity.setLocation(locationRepository.getOne(addFilmRequest.getLocation()));
//        scheduleFilmEntity.setStartTime(addFilmRequest.getStartTime().toString());
//        scheduleRepository.save(scheduleFilmEntity);
//        return addFilmRequest;
//    }
//
//    @Override
//    public Boolean deleteFilm(Long id) {
//        Optional<FilmEntity> filmEntity = filmRepository.findById(id);
//        if (filmEntity.isPresent()) {
//            filmEntity.get().setStatus(false);
//            filmRepository.save(filmEntity.get());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public Boolean deleteRoom(Long id) {
//        try {
//            roomRepository.deleteById(id);
//            return true;
//        } catch (Exception exception) {
//            return false;
//        }
//    }
//
//    @Override
//    public RoomEntity room(AddRoomRequest addRoomRequest) {
//        RoomEntity roomEntity = new RoomEntity();
//        roomEntity.setName(addRoomRequest.getName());
//        roomEntity.setId(addRoomRequest.getId());
//        roomEntity.setMaxSeats(addRoomRequest.getMaxSeats());
//        roomEntity.setSelectedSeat(addRoomRequest.getSelectedSeat());
//        roomEntity.setEmptySeats(addRoomRequest.getEmptySeats());
//        List<LocationEntity> locationEntities = new ArrayList<>();
//        LocationEntity locationEntity = new LocationEntity();
//        locationEntity.setLocation(addRoomRequest.getLocation());
//        locationEntities.add(locationEntity);
//        roomEntity.setLocation(locationEntities);
//        return roomRepository.save(roomEntity);
//    }
//
//    @Override
//    public UserEntity user(UserEntity userEntity) {
//        if (!userRepository.findByUsername(userEntity.getUsername()).isEmpty() && userEntity.getId() == null) {
//            throw new LoginFalseException("Username existed");
//        }
//        RoleEntity roleEntity = new RoleEntity();
//        roleEntity.setId(2L);
//        roleEntity.setName("Employee");
//        userEntity.setRoleEntity(roleEntity);
//        return userRepository.save(userEntity);
//    }
//
//    @Override
//    public Boolean deleteUser(Long id) {
//        userRepository.deleteById(id);
//        return true;
//    }
//
//    @Override
//    public List<UserEntity> getAllEmployee() {
//        RoleEntity roleEntity = new RoleEntity();
//        roleEntity.setId(2L);
//        roleEntity.setName("Employee");
//        return userRepository.findAllByRoleEntity(roleEntity);
//    }
//
//    @Override
//    public Integer getAllTicket() {
//        return ticketRepository.findAllByPayment(true).size();
//    }
//
//    @Override
//    public List<FilmEntity> getAllFilm() {
//        return filmRepository.getAllByStatusIs(true);
//    }
//
//    @Override
//    public List<RoomResponse> getAllRoom() {
//        List<RoomResponse> roomResponses = new ArrayList<>();
//        List<RoomEntity> roomEntities = roomRepository.findAll();
//        for (RoomEntity roomEntity : roomEntities) {
//            RoomResponse roomResponse = new RoomResponse();
//            roomResponse.setId(roomEntity.getId());
//            roomResponse.setName(roomEntity.getName());
//
//            if (roomEntity.getEmptySeats() != null) {
//                roomResponse.setEmptySeats(roomEntity.getEmptySeats());
//            }
//            if (roomEntity.getSelectedSeat() != null) {
//                roomResponse.setSelectedSeat(roomEntity.getSelectedSeat());
//            }
//
//            roomResponse.setMaxSeats(roomEntity.getMaxSeats());
//            if (roomEntity.getLocation().size() > 0) {
//                roomResponse.setLocation(roomEntity.getLocation().get(0).getLocation());
//
//            }
//            roomResponses.add(roomResponse);
//        }
//        return roomResponses;
//    }
//
//    @Override
//    public List<LocationEntity> getAllLocation() {
//        return locationRepository.findAll();
//    }
}
