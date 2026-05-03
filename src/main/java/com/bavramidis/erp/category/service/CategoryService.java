package com.bavramidis.erp.category.service;

import com.bavramidis.erp.category.dto.CategoryCreateDTO;
import com.bavramidis.erp.category.dto.CategoryResponseDTO;
import com.bavramidis.erp.category.dto.CategoryUpdateDTO;
import com.bavramidis.erp.category.entity.Category;
import com.bavramidis.erp.category.mapper.CategoryMapper;
import com.bavramidis.erp.category.repository.CategoryRepository;
import com.bavramidis.erp.exceptions.category.CategoryNotFoundException;
import com.bavramidis.erp.exceptions.category.CategoryValidationException;
import com.bavramidis.erp.exceptions.product.ProductNotFoundException;
import com.bavramidis.erp.product.dto.ProductResponseDTO;
import com.bavramidis.erp.product.dto.ProductUpdateDTO;
import com.bavramidis.erp.product.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
                .map(categoryMapper::createResponse)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Couldn't find category with id: " + categoryID));
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::createResponse)
                .toList();
    }

    @Transactional
    public CategoryResponseDTO createCategory(CategoryCreateDTO dto) {
        if (dto.name().equalsIgnoreCase("General")){
            throw new CategoryValidationException("The name General is reserved for system use.");
        }

        Category savedCategory = categoryRepository.save(categoryMapper.createEntity(dto));
        return categoryMapper.createResponse(savedCategory);
    }

    @Transactional
    public CategoryResponseDTO updateCategory(UUID categoryID, CategoryUpdateDTO dto) {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new CategoryNotFoundException("Couldn't find category with id: " + categoryID));

        categoryMapper.updateEntity(category, dto);

        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.createResponse(updatedCategory);
    }
}
