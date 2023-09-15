package com.g1elproyectegrande.repository;

import com.g1elproyectegrande.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{
    }