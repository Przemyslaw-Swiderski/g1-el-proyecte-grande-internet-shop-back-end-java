package com.g1elproyectegrande.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(
        Long id,
        String name,
        String description,
        String image,
        BigDecimal price,
        List<IdNamePairDto> productCategories,
        List<IdNamePairDto> productProducer

) {

}
