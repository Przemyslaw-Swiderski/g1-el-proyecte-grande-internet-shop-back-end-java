package com.g1elproyectegrande.controller;

import com.g1elproyectegrande.controller.dto.ProductDto;
import com.g1elproyectegrande.entity.Product;
import com.g1elproyectegrande.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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


    @GetMapping("/products/by-categories")
    public List<ProductDto> getAllProductsByCategoriesByGetMethod(@RequestParam List<Long> categoryIds) {
        List<ProductDto> products = productService.getProductsByCategoryIds(categoryIds);
        return products;
    }

    @PostMapping("/products-by-param")
//    @GetMapping(params="category" )
    public List<ProductDto> getAllProductsByCategoriesByGetMethodAnotherMethod(@RequestParam ("category") String categoryName) {
        List<ProductDto> products = productService.getProductsByCategoryName(categoryName);
        return products;
    }

    @GetMapping("/products/by-get-by-categories")
    public List<ProductDto> getAllProductsByCategories(@RequestHeader("Categories") String categoryIds) {
        // Split the comma-separated string into a list of Long
        List<Long> selectedCategoryIds = Arrays.stream(categoryIds.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        List<ProductDto> products = productService.getProductsByCategoryIds(selectedCategoryIds);
        return products;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }



    @PostMapping("/products/bycategories")
    public List<ProductDto> getAllProductsByCategoriesByPostMethod(@RequestBody Map<String, List<Long>> requestBody) {
        List<Long> selectedCategoryIds = requestBody.get("categoryIds");
        List<ProductDto> products = productService.getProductsByCategoryIds(selectedCategoryIds);
        return products;
    }

}
