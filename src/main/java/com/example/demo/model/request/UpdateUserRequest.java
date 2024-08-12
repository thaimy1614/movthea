package com.example.demo.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private Long id;
    private String password;
    private String email;
    private String name;
    private int age;
}
