package com.bavramidis.erp.product.service;

import com.bavramidis.erp.category.entity.Category;
import com.bavramidis.erp.category.repository.CategoryRepository;
import com.bavramidis.erp.exceptions.category.CategoryNotFoundException;
import com.bavramidis.erp.exceptions.product.ProductNotFoundException;
import com.bavramidis.erp.product.dto.ProductCreateDTO;
import com.bavramidis.erp.product.dto.ProductResponseDTO;
import com.bavramidis.erp.product.dto.ProductUpdateDTO;
import com.bavramidis.erp.product.entity.Product;
import com.bavramidis.erp.product.mapper.ProductMapper;
import com.bavramidis.erp.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public ProductResponseDTO getProduct(UUID productID) {
        return productRepository.findById(productID)
                .map(productMapper::createResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Couldn't find product with id: " + productID));
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAllWithCategory()
                .stream()
                .map(productMapper::createResponse)
                .toList();
    }

    @Transactional
    public ProductResponseDTO createProductWithCategory(ProductCreateDTO dto, UUID categoryID) {
        return saveProduct(dto, categoryRepository.findById(categoryID)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Couldn't find category with id: " + categoryID)));
    }

    @Transactional
    public ProductResponseDTO createProductWithoutCategory(ProductCreateDTO dto) {
        return saveProduct(dto, categoryRepository.findByNameIgnoreCase("General")
                .orElseThrow(() -> new IllegalStateException("Critical Error: 'General' category missing.")));
    }

    private ProductResponseDTO saveProduct(ProductCreateDTO dto, Category category) {
        Product product = productMapper.createEntity(dto);

        String sku = createSku(product.getName());
        product.setSku(sku);

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return productMapper.createResponse(savedProduct);
    }

    private String createSku(String productName){
        return productName.substring(0, 3).toUpperCase() +  "-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    @Transactional
    public ProductResponseDTO updateProduct(UUID productID, ProductUpdateDTO dto) {
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new ProductNotFoundException("Couldn't find product with id: " + productID));

        productMapper.updateEntity(product, dto);

        Optional.ofNullable(dto.categoryID())
                .filter(id -> !id.equals(product.getCategory().getCategoryID()))
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new CategoryNotFoundException("ID: " + id)))
                .ifPresent(product::setCategory);

        Product updatedProduct = productRepository.save(product);

        return productMapper.createResponse(updatedProduct);
    }
}