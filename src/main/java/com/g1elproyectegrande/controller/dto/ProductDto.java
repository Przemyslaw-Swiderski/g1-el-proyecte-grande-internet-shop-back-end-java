package com.g1elproyectegrande.controller.dto;

import java.math.BigDecimal;

public record ProductDto(
        long id,
        String title,
        String description,
        String image,
        BigDecimal price
        ) {

}
