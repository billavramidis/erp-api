package com.bavramidis.erp.warehouse.service;

import com.bavramidis.erp.warehouse.dto.WarehouseResponseDTO;
import com.bavramidis.erp.warehouse.mapper.WarehouseMapper;
import com.bavramidis.erp.warehouse.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    public List<WarehouseResponseDTO> getAllWarehouses() {
        return warehouseRepository.findAll()
                .stream()
                .map(warehouseMapper::toResponse)
                .toList();
    }
}
