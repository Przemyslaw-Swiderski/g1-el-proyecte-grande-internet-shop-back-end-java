package com.g1elproyectegrande.controller.dto;

import com.g1elproyectegrande.entity.ProductCategory;
import com.g1elproyectegrande.entity.ProductSupplier;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(
        Long id,
        String title,
        String description,
        String image,
        BigDecimal price,
        List<IdNamePairDto> productCategories,
        List<IdNamePairDto> productSuppliers

        ) {

}
