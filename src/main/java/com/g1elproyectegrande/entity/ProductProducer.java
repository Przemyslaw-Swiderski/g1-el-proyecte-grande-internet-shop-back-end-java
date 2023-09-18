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


//            cascade = CascadeType.ALL, // kaskada nie na many to many bo ryzyko ale one to many "może być"


    @OneToMany(
            mappedBy = "productProducer"
    )
//    private List<Product> products = new ArrayList<>(); //tak jest według Vlada // jak ordered annotacje na kolumnie to katastrofa wydajnośsdciowa
    private Set<Product> products = new HashSet<>(); // założyć equalsa

    public ProductProducer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}