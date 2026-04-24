package com.bavramidis.erp.product.service;

import com.bavramidis.erp.category.entity.Category;
import com.bavramidis.erp.category.service.CategoryService;
import com.bavramidis.erp.exceptions.ProductNotFoundException;
import com.bavramidis.erp.product.dto.ProductCreateDTO;
import com.bavramidis.erp.product.dto.ProductResponseDTO;
import com.bavramidis.erp.product.entity.Product;
import com.bavramidis.erp.product.mapper.ProductMapper;
import com.bavramidis.erp.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductMapper mapper;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.mapper = productMapper;
    }

    public ProductResponseDTO getProduct(UUID productID) {
        return productRepository.findById(productID)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Couldn't find product with id: " + productID));
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public ProductResponseDTO createProductWithCategory(ProductCreateDTO dto, UUID categoryID) {
        return saveProduct(dto, categoryService.getCategoryEntity(categoryID));
    }

    @Transactional
    public ProductResponseDTO createProductWithoutCategory(ProductCreateDTO dto) {
        return saveProduct(dto, categoryService.getDefaultCategory());
    }

    private ProductResponseDTO saveProduct(ProductCreateDTO dto, Category category){
        Product product = mapper.toEntity(dto);

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return mapper.toResponse(savedProduct);
    }
}
