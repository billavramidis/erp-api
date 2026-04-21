package com.bavramidis.erp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFoundError(ProductNotFoundException err){
        Map<String, Object> response = new HashMap<>();

        response.put("error", "Resource Not Found");
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("message", err.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralError(Exception err){
        Map<String, Object> response = new HashMap<>();

        response.put("error", "Internal Server Error");
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("message", err.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
