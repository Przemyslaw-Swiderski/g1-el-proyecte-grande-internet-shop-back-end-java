package com.g1elproyectegrande.controller;

import com.g1elproyectegrande.controller.dto.ProductDto;
import com.g1elproyectegrande.entity.Product;
import com.g1elproyectegrande.service.ProductService;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PermitAll //czy wpuści wszystko -?
//@RolesAllowed("user123")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
