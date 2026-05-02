package com.bavramidis.erp.product.entity;

import com.bavramidis.erp.category.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private UUID productID;

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

    @Column(updatable = false)
    private String sku;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "categoryID",
            nullable = false,
            columnDefinition = "BINARY(16)"
    )
    private Category category;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
