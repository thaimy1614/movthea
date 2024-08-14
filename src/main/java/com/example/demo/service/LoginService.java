package com.example.demo.service;

import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.response.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
