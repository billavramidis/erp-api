package com.bavramidis.erp.category.dto;

import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CategoryUpdateDTO(
        String name,

        String description,

        @PositiveOrZero(message = "The discount rate should be a positive number.")
        BigDecimal discountRate
) {
}
