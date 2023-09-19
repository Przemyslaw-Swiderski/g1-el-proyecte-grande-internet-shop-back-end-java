package com.g1elproyectegrande.controller;

import com.g1elproyectegrande.controller.dto.ProductCategoryDto;
import com.g1elproyectegrande.service.ProductCategoryService;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@PermitAll //czy wpuści wszystko -?
//@RolesAllowed("user123")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/categories")
    public List<ProductCategoryDto> getAllCategories() {
        return productCategoryService.getAllProductsCategories();
    }

//    @PostMapping("/categories")
//    public Product addProduct(@RequestBody Product product) {
//        return productService.addProduct(product);
//    }
}