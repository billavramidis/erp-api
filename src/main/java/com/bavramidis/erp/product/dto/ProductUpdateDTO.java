package com.bavramidis.erp.product.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductUpdateDTO(
        String name,

        @Positive(message = "The price should be a positive number.")
        BigDecimal price,

        String description,

        UUID categoryID
) {
}

