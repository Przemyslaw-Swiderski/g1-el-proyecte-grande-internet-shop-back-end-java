package com.g1elproyectegrande.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor //Default constructor for JPA
@Entity(name="Product")
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    @Column(unique = true)
    private String name;
    private String description;
    private String image;
    private BigDecimal price;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE}
    )

//    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)

    @JoinTable(name = "joining_products_and_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<ProductCategory> productCategories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductProducer productProducer;


    public Product(Long id, String name, String description, String image, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }
}
