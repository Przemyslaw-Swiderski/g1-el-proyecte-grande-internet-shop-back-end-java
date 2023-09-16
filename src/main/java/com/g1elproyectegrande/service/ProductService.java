package com.g1elproyectegrande.service;

import com.g1elproyectegrande.controller.dto.ProductDto;
import com.g1elproyectegrande.entity.Product;
import com.g1elproyectegrande.mapper.ProductMapper;
import com.g1elproyectegrande.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getAllProducts(){
        return productRepository.findAll().stream()
                .map(productMapper :: mapProductEntityToDto)
                .toList();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }
}

// Other methods for CRUD operations
