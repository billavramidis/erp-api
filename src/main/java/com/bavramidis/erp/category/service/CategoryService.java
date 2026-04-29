package com.bavramidis.erp.category.service;

import com.bavramidis.erp.category.dto.CategoryCreateDTO;
import com.bavramidis.erp.category.dto.CategoryResponseDTO;
import com.bavramidis.erp.category.entity.Category;
import com.bavramidis.erp.category.mapper.CategoryMapper;
import com.bavramidis.erp.category.repository.CategoryRepository;
import com.bavramidis.erp.exceptions.category.CategoryNotFoundException;
import com.bavramidis.erp.exceptions.category.CategoryValidationException;
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

    public CategoryResponseDTO getCategory(UUID categoryID){
        return categoryRepository.findById(categoryID)
                .map(categoryMapper::toResponse)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Couldn't find category with id: " + categoryID));
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
