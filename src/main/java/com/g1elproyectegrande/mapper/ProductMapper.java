package com.g1elproyectegrande.mapper;

import com.g1elproyectegrande.controller.dto.IdNamePairDto;
import com.g1elproyectegrande.controller.dto.ProductDto;
import com.g1elproyectegrande.entity.Product;

import java.util.List;

public class ProductMapper {

    public ProductDto mapProductEntityToDto(Product entity) {
        return new ProductDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage(),
                entity.getPrice(),
                mapProductsCategories(entity),
                mapProductsSuppliers(entity)
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

    private static List<IdNamePairDto> mapProductsCategories(Product entity) {
        return entity.getProductCategories().stream()
                .map(pc -> new IdNamePairDto(pc.getId(), pc.getName()))
                .toList();
    }

    private static List<IdNamePairDto> mapProductsSuppliers(Product entity) {
        return entity.getProductSuppliers().stream()
                .map(pc -> new IdNamePairDto(pc.getId(), pc.getName()))
                .toList();
    }


}
