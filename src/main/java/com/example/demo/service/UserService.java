package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;

public interface UserService {
    UserEntity findByUsername(String username);

    void createUser(String username, String password);

    void updateProfile(Long userId, UserEntity newUserProfile);

    void changePassword(String username, String oldPassword, String newPassword);
}
