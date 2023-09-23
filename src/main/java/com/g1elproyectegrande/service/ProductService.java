package com.g1elproyectegrande.service;

import com.g1elproyectegrande.controller.dto.ProductDto;
import com.g1elproyectegrande.entity.Product;
import com.g1elproyectegrande.mapper.ProductMapper;
import com.g1elproyectegrande.repository.ProductCategoryRepository;
import com.g1elproyectegrande.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
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

    public List<ProductDto> getProductsByCategoryIds(List<Long> categoryIds) {
//        List<ProductCategory> categories = productCategoryRepository.findAllById(categoryIds);
//        List<Long> categories = categoryIds;
//        if (!categories.isEmpty()) {
//        if (!categoryIds.isEmpty()) {
            return productRepository.findByCategoryIds(categoryIds).stream()
                    .map(productMapper :: mapProductEntityToDto)
                    .toList();

//            return productRepository.findByCategoryIds(categoryIds);
//        }
//        return Collections.emptyList();
    }


    public List<ProductDto> getProductsByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName).stream()
                .map(productMapper :: mapProductEntityToDto)
                .toList();
    }
}

// Other methods for CRUD operations
