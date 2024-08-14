package com.example.demo.exception;

public class LoginFalseException extends RuntimeException {
    public LoginFalseException(String message) {
        super(message);
    }
}
