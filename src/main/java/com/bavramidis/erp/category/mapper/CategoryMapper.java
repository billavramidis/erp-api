package com.bavramidis.erp.category.mapper;

import com.bavramidis.erp.category.dto.CategoryCreateDTO;
import com.bavramidis.erp.category.dto.CategoryResponseDTO;
import com.bavramidis.erp.category.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryCreateDTO dto){
        if (dto == null)
            return null;

        return new Category(
                dto.name(),
                dto.description(),
                dto.discountRate()
        );
    }

    public CategoryResponseDTO toResponse(Category category) {
        if (category == null) {
            return null;
        }

        return new CategoryResponseDTO(
                category.getCategoryID(),
                category.getName(),
                category.getDiscountRate(),
                category.getDescription()
        );
    }
}
