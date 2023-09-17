package com.g1elproyectegrande.service;

import com.g1elproyectegrande.controller.dto.ProductProducerDto;
import com.g1elproyectegrande.mapper.ProductProducerMapper;
import com.g1elproyectegrande.repository.ProductProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductProducerService {
    private final ProductProducerRepository productProducerRepository;

    private final ProductProducerMapper productProducerMapper;

    public ProductProducerService(ProductProducerRepository productProducerRepository, ProductProducerMapper productProducerMapper) {
        this.productProducerRepository = productProducerRepository;
        this.productProducerMapper = productProducerMapper;
    }

    public List<ProductProducerDto> getAllProductsProducers(){
        return productProducerRepository.findAll().stream()
                .map(productProducerMapper :: mapProductProducerEntityToDto)
                .toList();
    }
}
