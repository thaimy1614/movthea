package com.example.demo.exception;

public class AccountExistedException extends RuntimeException {

    public AccountExistedException(String message) {
        super(message);
    }
}
