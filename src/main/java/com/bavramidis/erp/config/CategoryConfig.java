package com.bavramidis.erp.config;

import com.bavramidis.erp.category.entity.Category;
import com.bavramidis.erp.category.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class CategoryConfig {
    private final CategoryRepository categoryRepository;

    public CategoryConfig(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Bean
    public CommandLineRunner createDefaultCategory(){
        return args -> {
            if (!categoryRepository.existsByName("General")){
                categoryRepository.save(new Category("General", "Default Category", BigDecimal.ZERO));
            }
        };
    }

}
