package com.bavramidis.erp.category.controller;

import com.bavramidis.erp.category.dto.CategoryCreateDTO;
import com.bavramidis.erp.category.dto.CategoryResponseDTO;
import com.bavramidis.erp.category.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        List<CategoryResponseDTO> response = categoryService.getAllCategories();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable UUID categoryID){
        CategoryResponseDTO response = categoryService.getCategory(categoryID);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryCreateDTO dto){
        CategoryResponseDTO response = categoryService.createCategory(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
