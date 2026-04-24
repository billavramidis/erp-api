package com.bavramidis.erp.category.repository;

import com.bavramidis.erp.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    boolean existsByName(String name);
    Optional<Category> findByNameIgnoreCase(String name);
}
