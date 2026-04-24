package com.bavramidis.erp.exceptions;

public class CategoryValidationException extends IllegalArgumentException {
    public CategoryValidationException(String message) {
        super(message);
    }
}
