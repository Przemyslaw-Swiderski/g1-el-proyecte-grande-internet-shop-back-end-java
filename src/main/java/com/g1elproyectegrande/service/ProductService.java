package com.g1elproyectegrande.service;

import com.g1elproyectegrande.entity.Product;
import com.g1elproyectegrande.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return new ArrayList<>(productRepository.findAll());
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }
}

// Other methods for CRUD operations
