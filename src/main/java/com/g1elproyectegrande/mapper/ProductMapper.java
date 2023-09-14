package com.g1elproyectegrande.mapper;

import com.g1elproyectegrande.controller.dto.ProductDto;
import com.g1elproyectegrande.entity.Product;

public class ProductMapper {

    public ProductDto mapProductEntityToDto(Product entity) {
        return new ProductDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage(),
                entity.getPrice()
            );
        }


    public Product mapProductDtoToEntity(ProductDto dto) {
        return new Product(
                dto.id(),
                dto.title(),
                dto.description(),
                dto.image(),
                dto.price()
        );
    }
}
