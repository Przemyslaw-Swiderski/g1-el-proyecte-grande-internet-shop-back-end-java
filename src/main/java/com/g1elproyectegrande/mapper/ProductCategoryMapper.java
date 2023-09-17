package com.g1elproyectegrande.mapper;

import com.g1elproyectegrande.controller.dto.ProductCategoryDto;
import com.g1elproyectegrande.entity.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper {
    public ProductCategoryDto mapProductCategoryEntityToDto(ProductCategory entity) {
        return new ProductCategoryDto(
                entity.getId(),
                entity.getName()
//                mapProducts(entity)
        );
    }

    public ProductCategory mapProductCategoryDtoToEntity(ProductCategoryDto dto) {
        return new ProductCategory(
                dto.id(),
                dto.name()
        );
    }

//    Teraz tego nie używamy ponieważ w tym Dto nie potrzebujemy listy powiązanych produktów
//    private static List<IdNamePairDto> mapProducts(ProductCategory entity) {
//        return entity.getProducts().stream()
//                .map(p -> new IdNamePairDto(p.getId(), p.getName()))
//                .toList();
//    }

}
