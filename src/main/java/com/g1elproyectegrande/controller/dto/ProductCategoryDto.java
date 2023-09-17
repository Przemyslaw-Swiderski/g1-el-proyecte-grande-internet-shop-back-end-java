package com.g1elproyectegrande.controller.dto;

public record ProductCategoryDto(
        Long id,
        String name
//        List<IdNamePairDto>products // teraz nie dajemy ponieważ pociągnie nam wszystkie powiązane produkty
) {
}
