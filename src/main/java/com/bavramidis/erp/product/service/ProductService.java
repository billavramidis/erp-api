package com.bavramidis.erp.product.service;

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
    private final ProductMapper mapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.mapper = productMapper;
    }

    public ProductResponseDTO getProduct(UUID id) {
        return productRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Couldn't find product with id: " + id));
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public ProductResponseDTO createProduct(ProductCreateDTO dto) {
        //Add custom logic for SKU create and after save.
        Product savedProduct = productRepository.save(mapper.toEntity(dto));

        return mapper.toResponse(savedProduct);
    }
}
