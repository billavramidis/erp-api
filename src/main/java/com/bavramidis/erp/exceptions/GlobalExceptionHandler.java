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
    public ResponseEntity<Map<String, Object>> handleProductNotFoundError(ProductNotFoundException err) {
        Map<String, Object> response = buildResponse("Product Not Found",
                HttpStatus.NOT_FOUND,
                err.getMessage(),
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCategoryNotFoundError(CategoryNotFoundException err) {
        Map<String, Object> response = buildResponse("Category Not Found",
                HttpStatus.NOT_FOUND,
                err.getMessage(),
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CategoryValidationException.class)
    public ResponseEntity<Map<String, Object>> handleCategoryError(CategoryValidationException err) {
        Map<String, Object> response = buildResponse("Category Business Rule Violation",
                HttpStatus.BAD_REQUEST,
                err.getMessage(),
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralError(Exception err) {
        Map<String, Object> response = buildResponse("Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR,
                err.getMessage(),
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    //Returns all MethodInvalidArgument Errors at once for easier debugging.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidError(MethodArgumentNotValidException err) {
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

    //Builds the responses for the exception handlers. Reduces boilerplate lol.
    private Map<String, Object> buildResponse(String errorDescription, HttpStatus httpStatus,
                                              String errorMessage, LocalDateTime timestamp) {

        Map<String, Object> response = new HashMap<>();

        response.put("error", errorDescription);
        response.put("status", httpStatus.value());
        response.put("message", errorMessage);
        response.put("timestamp", timestamp);

        return response;
    }
}
