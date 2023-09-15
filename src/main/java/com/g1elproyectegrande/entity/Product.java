package com.g1elproyectegrande.entity;

import com.g1elproyectegrande.controller.dto.ProductCategoryDto;
import com.g1elproyectegrande.entity.ProductCategory;

import lombok.*;
import org.hibernate.type.UrlType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor //Default constructor for JPA
@Entity
@ToString
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String image;
    private BigDecimal price;
    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "products_joining_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<ProductCategory> productCategories = new HashSet<>();

    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "products_joining_suppliers",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    private Set<ProductSupplier> productSuppliers = new HashSet<>();


    public Product(long id, String title, String description, String image, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
    }


}
