package com.bavramidis.erp.warehouse.dto;

import java.util.UUID;

public record WarehouseResponseDTO(
        UUID warehouseID,

        String name,

        Long capacity
) {
}
