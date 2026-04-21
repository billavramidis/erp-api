package com.bavramidis.erp.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID id,
                                 String name,
                                 BigDecimal price,
                                 String sku,
                                 String description
) {
}
