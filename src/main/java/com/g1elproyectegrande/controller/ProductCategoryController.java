package com.g1elproyectegrande.controller;

import com.g1elproyectegrande.controller.dto.ProductCategoryDto;
import com.g1elproyectegrande.service.ProductCategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//@PermitAll //czy wpuści wszystko -?
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

//    @RolesAllowed("admin")
    @GetMapping("/auth-only-categories")
    public List<ProductCategoryDto> getAllCategoriesOnlyForAdmins() {
        return productCategoryService.getAllProductsCategories();
    }


//    @PostMapping("/categories")
//    public Product addProduct(@RequestBody Product product) {
//        return productService.addProduct(product);
//    }
}