package com.bavramidis.erp.category.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CategoryResponseDTO(
        UUID categoryID,

        String name,

        String description,

        BigDecimal discountRate
) {
}
