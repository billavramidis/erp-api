package com.bavramidis.erp.product.mapper;

import com.bavramidis.erp.product.dto.ProductCreateDTO;
import com.bavramidis.erp.product.dto.ProductResponseDTO;
import com.bavramidis.erp.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ProductMapper{
    public abstract Product createDTOToEntity(ProductCreateDTO productCreateDTO);

    @Mapping(target = "categoryName", expression = "java(product.getCategory().getName())")
    public abstract ProductResponseDTO toResponse(Product product);
}
