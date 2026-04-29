package com.bavramidis.erp.exceptions.category;

public class CategoryValidationException extends IllegalArgumentException {
    public CategoryValidationException(String message) {
        super(message);
    }
}
