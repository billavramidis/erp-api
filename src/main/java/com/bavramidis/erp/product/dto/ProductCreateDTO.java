package com.bavramidis.erp.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductCreateDTO(
        @NotBlank(message = "The name of the product may not be blank.")
        String name,
        @NotNull(message = "The price cannot contain a null value.")
        @Positive(message = "The price should be a positive number.")
        BigDecimal price,
        String description) {
}

