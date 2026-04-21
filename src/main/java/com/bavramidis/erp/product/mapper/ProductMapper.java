package com.bavramidis.erp.product.mapper;

import com.bavramidis.erp.product.dto.ProductCreateDTO;
import com.bavramidis.erp.product.dto.ProductResponseDTO;
import com.bavramidis.erp.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductCreateDTO dto){
        if (dto == null)
            return null;

        return new Product(
                dto.name(),
                dto.description(),
                dto.price()
        );
    }

    public ProductResponseDTO toResponse(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getSku(),
                product.getDescription()
        );
    }
}
