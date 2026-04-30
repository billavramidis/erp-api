package com.bavramidis.erp.category.mapper;

import com.bavramidis.erp.category.dto.CategoryCreateDTO;
import com.bavramidis.erp.category.dto.CategoryResponseDTO;
import com.bavramidis.erp.category.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper{
    Category createEntity(CategoryCreateDTO categoryCreateDTO);
    CategoryResponseDTO createResponse(Category category);
}
