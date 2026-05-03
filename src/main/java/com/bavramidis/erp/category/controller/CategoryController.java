package com.bavramidis.erp.category.controller;

import com.bavramidis.erp.category.dto.CategoryCreateDTO;
import com.bavramidis.erp.category.dto.CategoryResponseDTO;
import com.bavramidis.erp.category.dto.CategoryUpdateDTO;
import com.bavramidis.erp.category.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        List<CategoryResponseDTO> response = categoryService.getAllCategories();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable UUID categoryID){
        CategoryResponseDTO response = categoryService.getCategory(categoryID);

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryCreateDTO dto){
        CategoryResponseDTO response = categoryService.createCategory(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping()
    public ResponseEntity<CategoryResponseDTO> updateCategory(UUID categoryID,
                                                              @RequestBody @Valid CategoryUpdateDTO dto){
        CategoryResponseDTO response = categoryService.updateCategory(categoryID, dto);

        return ResponseEntity.ok(response);
    }
}
