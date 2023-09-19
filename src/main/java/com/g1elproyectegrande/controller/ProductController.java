package com.g1elproyectegrande.controller;

import com.g1elproyectegrande.controller.dto.ProductDto;
import com.g1elproyectegrande.entity.Product;
import com.g1elproyectegrande.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


//@PermitAll //stsujemy bez configa, żeby się nie mieszało
//@RolesAllowed(".....")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @PermitAll
//    @RolesAllowed("xyz")
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products/bycategories")
    public List<ProductDto> getAllProductsByCategories(@RequestBody Map<String, List<Long>> requestBody) {
        List<Long> selectedCategoryIds = requestBody.get("categoryIds");
        List<ProductDto> products = productService.getProductsByCategoryIds(selectedCategoryIds);
        return products;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
