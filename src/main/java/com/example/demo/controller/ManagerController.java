package com.example.demo.controller;

import com.example.demo.model.entity.FilmEntity;
import com.example.demo.model.entity.LocationEntity;
import com.example.demo.model.entity.RoomEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.request.AddFilmRequest;
import com.example.demo.model.request.AddRoomRequest;
import com.example.demo.model.response.RoomResponse;
import com.example.demo.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {
    ManagerService managerService;

    @PostMapping("")
    public ResponseEntity<AddFilmRequest> addFilm(@RequestBody AddFilmRequest filmEntity) {
        return ResponseEntity.ok(managerService.addFilm(filmEntity));
    }

    @DeleteMapping("/film/{id}")
    public ResponseEntity<Boolean> deleteFilm(@PathVariable Long id) {
        return ResponseEntity.ok(managerService.deleteFilm(id));
    }

    @PostMapping("/room")
    public ResponseEntity<RoomEntity> Room(@RequestBody AddRoomRequest roomEntity) {
        return ResponseEntity.ok(managerService.room(roomEntity));
    }

    @PostMapping("/staff")
    public ResponseEntity<UserEntity> employee(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ok(managerService.user(userEntity));
    }


    @DeleteMapping("/staff/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(managerService.deleteUser(id));
    }

    @GetMapping("/staff")
    public ResponseEntity<List<UserEntity>> getAllEmployee() {
        return ResponseEntity.ok(managerService.getAllEmployee());
    }

    @GetMapping("/ticket")
    public ResponseEntity<Integer> getAllTicket() {
        return ResponseEntity.ok(managerService.getAllTicket());
    }


    @GetMapping("/film")
    public ResponseEntity<List<FilmEntity>> getAllFilm() {
        return ResponseEntity.ok(managerService.getAllFilm());
    }

    @GetMapping("/room")
    public ResponseEntity<List<RoomResponse>> getAllRoom() {
        return ResponseEntity.ok(managerService.getAllRoom());
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<Boolean> deleteRoom(@PathVariable Long id) {
        return ResponseEntity.ok(managerService.deleteRoom(id));
    }

    @GetMapping("/location")
    public ResponseEntity<List<LocationEntity>> getAllLocation() {
        return ResponseEntity.ok(managerService.getAllLocation());
    }

}
