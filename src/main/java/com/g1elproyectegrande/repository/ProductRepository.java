package com.g1elproyectegrande.repository;

import com.g1elproyectegrande.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p JOIN FETCH p.productCategories c WHERE c.id IN :categoryIds")
    List<Product> findByCategoryIds(@Param("categoryIds") List<Long> categoryIds);

    @Query("SELECT p FROM Product p JOIN FETCH p.productCategories c WHERE c.name=:categoryName")
    List<Product> findByCategoryName(String categoryName);

}

//open session inview

