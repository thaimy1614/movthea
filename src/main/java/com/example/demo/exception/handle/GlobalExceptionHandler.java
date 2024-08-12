package com.example.demo.exception.handle;

import com.example.demo.exception.AccountExistedException;
import com.example.demo.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    public static final String KEY = "error";

    @ExceptionHandler(AccountExistedException.class)
    public ErrorResponse<Map<String, String>> handleMissingServletRequestParameter(
            AccountExistedException ex) {
        String message = "Account existed";
        String code = "400";
        ErrorResponse<Map<String, String>> error = new ErrorResponse<>(message, code);
        return error.fail(Collections.singletonMap(KEY, message));
    }

}
