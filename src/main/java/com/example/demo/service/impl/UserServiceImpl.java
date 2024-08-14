package com.example.demo.service.impl;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void createUser(String username, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(UserEntity.UserRole.USER);
        userRepository.save(user);
    }

    public void updateProfile(Long userId, UserEntity newProfile) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        user.setEmail(newProfile.getEmail());
        user.setName(newProfile.getName());
        user.setAge(newProfile.getAge());
        userRepository.save(user);
    }


    public void changePassword(String username, String oldPassword, String newPassword) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }
        userRepository.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAllByRoleEquals(UserEntity.UserRole.USER);
    }

    public List<UserEntity> searchUser(String username, String name, String email) {
        return userRepository.findByUsernameContainingOrNameContainingOrEmailContaining(username, name, email);
    }
}
