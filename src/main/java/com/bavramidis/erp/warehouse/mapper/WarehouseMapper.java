package com.bavramidis.erp.warehouse.mapper;

import com.bavramidis.erp.warehouse.dto.WarehouseResponseDTO;
import com.bavramidis.erp.warehouse.entity.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseResponseDTO toResponse(Warehouse warehouse);
}
