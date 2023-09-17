package com.g1elproyectegrande.controller.auth.dto;


public record JwtTokenResponse(
        String accessToken,
        String refreshToken,
        String userEmail,
        String userName,
        String userSurname) {
}

