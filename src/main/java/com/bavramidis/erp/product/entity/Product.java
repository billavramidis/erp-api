package com.bavramidis.erp.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NonNull
    @NotBlank(message = "The name of the product may not be blank.")
    private String name;

    @NonNull
    private String description;

    @Column(precision = 10, scale = 2)
    @NonNull
    @NotNull(message = "The price cannot contain a null value.")
    @Positive(message = "The price should be a positive number.")
    private BigDecimal price;

    @Setter(AccessLevel.NONE)
    private String sku;
}
