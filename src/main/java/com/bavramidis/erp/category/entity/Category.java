package com.bavramidis.erp.category.entity;

import com.bavramidis.erp.product.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Category {
    public static final String DEFAULT_CATEGORY_NAME = "Default";
    public static final String DEFAULT_CATEGORY_DESCRIPTION = "Default Category";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "BINARY(16)")
    private UUID categoryID;

    @NonNull
    @NotNull(message = "The name should not contain a null value.")
    @NotBlank(message = "The name of the category should not be blank.")
    private String name;

    @NonNull
    @NotNull(message = "The description should not contain a null value.")
    private String description;

    @NonNull
    @NotNull(message = "The discount rate should not contain a null value.")
    @Column(name = "discount_rate", precision = 10, scale = 2)
    @PositiveOrZero(message = "The discount rate should be a positive or zero number.")
    private BigDecimal discountRate;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
