package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private int age;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public enum UserRole {
        ADMIN,
        USER
    }
}
