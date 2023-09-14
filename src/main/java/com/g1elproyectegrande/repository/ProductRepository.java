package com.g1elproyectegrande.repository;

import com.g1elproyectegrande.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

