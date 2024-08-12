package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity findByUsername(String username);

    void createUser(String username, String password);

    void updateProfile(Long userId, UserEntity newUserProfile);

    void changePassword(String username, String oldPassword, String newPassword);

    List<UserEntity> getAllUsers();

    List<UserEntity> searchUser(String username, String name, String email);
}
