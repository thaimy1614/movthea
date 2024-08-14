package com.example.demo.model.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginResponse {
    private String name;
    private String token;
    private Long role;
    private Long userId;
}
