package com.bavramidis.erp.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CategoryCreateDTO(
        @NotBlank(message = "The name of the category may not be blank.")
        String name,
        @NotNull(message = "The price cannot contain a null value.")
        @PositiveOrZero(message = "The discount rate should be a positive number.")
        BigDecimal discountRate,
        String description) {
}

