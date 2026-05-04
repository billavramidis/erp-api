package com.bavramidis.erp.category.service;

import com.bavramidis.erp.category.dto.CategoryCreateDTO;
import com.bavramidis.erp.category.dto.CategoryResponseDTO;
import com.bavramidis.erp.category.dto.CategoryUpdateDTO;
import com.bavramidis.erp.category.entity.Category;
import com.bavramidis.erp.category.mapper.CategoryMapper;
import com.bavramidis.erp.category.repository.CategoryRepository;
import com.bavramidis.erp.exceptions.category.CategoryNotFoundException;
import com.bavramidis.erp.exceptions.category.CategoryValidationException;
import com.bavramidis.erp.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
    }

    public CategoryResponseDTO getCategory(UUID categoryID) {
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
        if (dto.name().equalsIgnoreCase(Category.DEFAULT_CATEGORY_NAME)) {
            throw new CategoryValidationException("The name General is reserved for system use.");
        }

        Category savedCategory = categoryRepository.save(categoryMapper.createEntity(dto));
        return categoryMapper.createResponse(savedCategory);
    }

    @Transactional
    public CategoryResponseDTO updateCategory(UUID categoryID, CategoryUpdateDTO dto) {
        if (dto.name().equalsIgnoreCase(Category.DEFAULT_CATEGORY_NAME)) {
            throw new CategoryValidationException("The name General is reserved for system use.");
        }

        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new CategoryNotFoundException("Couldn't find category with id: " + categoryID));

        if (category.getName().equalsIgnoreCase(Category.DEFAULT_CATEGORY_NAME)) {
            throw new CategoryValidationException("The General category cannot be modified.");
        }

        categoryMapper.updateEntity(category, dto);

        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.createResponse(updatedCategory);
    }

    @Transactional
    public void deleteCategory(UUID categoryID) {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new CategoryNotFoundException("ID " + categoryID + " not found"));

        if (category.getName().equalsIgnoreCase(Category.DEFAULT_CATEGORY_NAME)) {
            throw new CategoryValidationException("The General category cannot be deleted.");
        }

        UUID generalCategoryID = categoryRepository.findByNameIgnoreCase(Category.DEFAULT_CATEGORY_NAME)
                .orElseThrow(() -> new IllegalStateException("Critical Error: 'General' category missing."))
                .getCategoryID();

        productRepository.updateCategory(generalCategoryID, categoryID);
        categoryRepository.delete(category);
    }
}
