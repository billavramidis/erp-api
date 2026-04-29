package com.bavramidis.erp.product.mapper;

import com.bavramidis.erp.product.dto.ProductCreateDTO;
import com.bavramidis.erp.product.dto.ProductResponseDTO;
import com.bavramidis.erp.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper{
    Product createDTOToEntity(ProductCreateDTO productCreateDTO);
    ProductResponseDTO toResponse(Product product);
}
