package com.bavramidis.erp.category.mapper;

import com.bavramidis.erp.category.dto.CategoryCreateDTO;
import com.bavramidis.erp.category.dto.CategoryResponseDTO;
import com.bavramidis.erp.category.dto.CategoryUpdateDTO;
import com.bavramidis.erp.category.entity.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper{
    Category createEntity(CategoryCreateDTO categoryCreateDTO);
    CategoryResponseDTO createResponse(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Category category, CategoryUpdateDTO dto);
}
