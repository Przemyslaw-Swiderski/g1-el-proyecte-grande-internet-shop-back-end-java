package com.g1elproyectegrande.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity(name="ProductProducer")
@Setter
@Getter
@NoArgsConstructor //Default constructor for JPA
@ToString
@Table(name = "products_producers")
public class ProductProducer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    private String name;

//    @OneToMany(
//            mappedBy = "products_producers",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
    @OneToMany(
            mappedBy = "productProducer"
    )
//    private List<Product> products = new ArrayList<>(); //tak jest według Vlada
    private Set<Product> products = new HashSet<>();

    public ProductProducer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}