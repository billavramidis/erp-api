package com.bavramidis.erp.category.service;

import com.bavramidis.erp.category.dto.CategoryCreateDTO;
import com.bavramidis.erp.category.dto.CategoryResponseDTO;
import com.bavramidis.erp.category.entity.Category;
import com.bavramidis.erp.category.mapper.CategoryMapper;
import com.bavramidis.erp.category.repository.CategoryRepository;
import com.bavramidis.erp.exceptions.CategoryNotFoundException;
import com.bavramidis.erp.exceptions.CategoryValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    //Returns a DTO, to be used strictly in CategoryController.
    public CategoryResponseDTO getCategoryResponse(UUID categoryID) {
        return categoryMapper.toResponse(getCategoryEntity(categoryID));
    }

    //Returns a Category Entity, useful for other services.
    public Category getCategoryEntity(UUID categoryID){
        return categoryRepository.findById(categoryID)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Couldn't find category with id: " + categoryID));
    }

    //Returns the Default Category Entity, useful for other services.
    public Category getDefaultCategory() {
        return categoryRepository.findByNameIgnoreCase("General")
                .orElseThrow(() -> new IllegalStateException("Critical Error: 'General' category missing."));
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Transactional
    public CategoryResponseDTO createCategory(CategoryCreateDTO dto) {
        if (dto.name().equalsIgnoreCase("General")){
            throw new CategoryValidationException("The name General is reserved for system use.");
        }

        Category savedCategory = categoryRepository.save(categoryMapper.toEntity(dto));
        return categoryMapper.toResponse(savedCategory);
    }
}
