package com.g1elproyectegrande.controller;

import com.g1elproyectegrande.controller.dto.ProductProducerDto;
import com.g1elproyectegrande.service.ProductProducerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductProducerController {
    private final ProductProducerService productProducerService;

    public ProductProducerController(ProductProducerService productProducerService) {
        this.productProducerService = productProducerService;
    }

    @GetMapping("/producers")
    public List<ProductProducerDto> getAllProductsProducers() {
        return productProducerService.getAllProductsProducers();
    }

}
