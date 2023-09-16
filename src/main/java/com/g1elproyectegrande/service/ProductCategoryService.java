package com.g1elproyectegrande.service;

import com.g1elproyectegrande.controller.dto.ProductCategoryDto;
import com.g1elproyectegrande.mapper.ProductCategoryMapper;
import com.g1elproyectegrande.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;
    }

    public List<ProductCategoryDto> getAllProductsCategories(){
        return productCategoryRepository.findAll().stream()
                .map(productCategoryMapper :: mapProductCategoryEntityToDto)
                .toList();
    }
}
