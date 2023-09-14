package com.g1elproyectegrande.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor //Default constructor for JPA
@Entity
@ToString
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String image;
    private BigDecimal price; //big decimall -> currency object
//    private long id_category; //use objects instead of id's
//    private long id_supplier;


    //     Constructor without id for creating new instances
//    public Product(long id, String title, String description, String image, double price, long id_category, long id_supplier) {
    public Product(long id, String title, String description, String image, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
//        this.id_category = id_category;
//        this.id_supplier = id_supplier;
    }


}
