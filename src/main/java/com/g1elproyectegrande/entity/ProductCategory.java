package com.g1elproyectegrande.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name="ProductCategory")
@Getter
@Setter
@NoArgsConstructor //Default constructor for JPA
@ToString
@Table(name = "products_categories")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    private String name;
    @ManyToMany(mappedBy = "productCategories")
    private Set<Product> products = new HashSet<>();

    public ProductCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }


//    public void addProduct(Product p) {
//        products.add(p);
//    }
}
