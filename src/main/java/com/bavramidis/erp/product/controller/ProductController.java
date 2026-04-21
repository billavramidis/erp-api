package com.bavramidis.erp.product.controller;

import com.bavramidis.erp.product.dto.ProductCreateDTO;
import com.bavramidis.erp.product.dto.ProductResponseDTO;
import com.bavramidis.erp.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable UUID id) {
        ProductResponseDTO response = productService.getProduct(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> response = productService.getAllProducts();

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductCreateDTO dto) {
        ProductResponseDTO response = productService.createProduct(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
