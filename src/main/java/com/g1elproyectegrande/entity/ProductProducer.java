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
//    private List<PostComment> comments = new ArrayList<>();
//    private List<Product> products = new ArrayList<>();
    private Set<Product> products = new HashSet<>();
}