package com.bavramidis.erp.product.controller;

import com.bavramidis.erp.product.dto.ProductCreateDTO;
import com.bavramidis.erp.product.dto.ProductResponseDTO;
import com.bavramidis.erp.product.dto.ProductUpdateDTO;
import com.bavramidis.erp.product.service.ProductService;
import jakarta.validation.Valid;
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

    @GetMapping("/{productID}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable UUID productID) {
        ProductResponseDTO response = productService.getProduct(productID);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> response = productService.getAllProducts();

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> createProductWithoutCategory(@RequestBody @Valid ProductCreateDTO dto) {
        ProductResponseDTO response = productService.createProductWithoutCategory(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{categoryID}")
    public ResponseEntity<ProductResponseDTO> createProductWithCategory(@RequestBody @Valid ProductCreateDTO dto,
                                                                        @PathVariable UUID categoryID) {
        ProductResponseDTO response = productService.createProductWithCategory(dto, categoryID);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{productID}")
    public ResponseEntity<ProductResponseDTO> updateProductCategory(@PathVariable UUID productID,
                                                                    @RequestBody UUID categoryID) {
        ProductResponseDTO response = productService.updateProductCategory(productID, categoryID);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{productID}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID productID,
                                                            @RequestBody @Valid ProductUpdateDTO dto) {
        ProductResponseDTO response = productService.updateProduct(productID, dto);

        return ResponseEntity.ok(response);
    }
}
