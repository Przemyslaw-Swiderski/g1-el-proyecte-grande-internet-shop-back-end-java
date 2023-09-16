package com.g1elproyectegrande.mapper;

import com.g1elproyectegrande.controller.dto.IdNamePairDto;
import com.g1elproyectegrande.controller.dto.ProductDto;
import com.g1elproyectegrande.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public ProductDto mapProductEntityToDto(Product entity) {
        return new ProductDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage(),
                entity.getPrice(),
                mapProductsCategories(entity)
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
                .map(p -> new IdNamePairDto(p.getId(), p.getName()))
                .toList();
    }

//    private static List<IdNamePairDto> mapProductsSuppliers(Product entity) {
//        return entity.getProductProducer().stream()
//                .map(p -> new IdNamePairDto(p.getId(), p.getName()))
//                .toList();
//    }


}
