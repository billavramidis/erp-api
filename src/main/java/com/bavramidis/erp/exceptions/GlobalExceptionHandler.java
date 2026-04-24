package com.bavramidis.erp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

        response.put("error", "Product Not Found");
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("message", err.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCategoryNotFoundError(CategoryNotFoundException err){
        Map<String, Object> response = new HashMap<>();

        response.put("error", "Category Not Found");
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("message", err.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidError(MethodArgumentNotValidException err){
        Map<String, Object> response = new HashMap<>();
        Map<String, String> invalidErrors = new HashMap<>();

        err.getBindingResult().getFieldErrors().forEach(error ->
                    invalidErrors.put(error.getField(), error.getDefaultMessage())
                );

        response.put("error", "Bad Request");
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("messages", invalidErrors);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(CategoryValidationException.class)
    public ResponseEntity<Map<String, Object>> handleCategoryError(CategoryValidationException err){
        Map<String, Object> response = new HashMap<>();

        response.put("error", "Category Business Rule Violation");
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", err.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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
