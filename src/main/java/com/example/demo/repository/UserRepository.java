package com.example.demo.repository;

import com.example.demo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByUsernameContainingOrNameContainingOrEmailContaining(String username, String name, String email);

    List<UserEntity> findAllByRoleEquals(UserEntity.UserRole role);
}
