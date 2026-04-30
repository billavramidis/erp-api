package com.bavramidis.erp.product.mapper;

import com.bavramidis.erp.product.dto.ProductCreateDTO;
import com.bavramidis.erp.product.dto.ProductResponseDTO;
import com.bavramidis.erp.product.dto.ProductUpdateDTO;
import com.bavramidis.erp.product.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ProductMapper{
    public abstract Product createEntity(ProductCreateDTO productCreateDTO);

    @Mapping(target = "categoryName", expression = "java(product.getCategory().getName())")
    public abstract ProductResponseDTO createResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)
    public abstract void updateEntity(@MappingTarget Product product, ProductUpdateDTO dto);
}
