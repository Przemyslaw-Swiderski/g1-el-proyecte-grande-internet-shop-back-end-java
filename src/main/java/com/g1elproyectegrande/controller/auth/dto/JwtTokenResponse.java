package com.g1elproyectegrande.controller.auth.dto;

//public record JwtTokenResponse(String token) {
//}
public record JwtTokenResponse(String accessToken, String refreshToken, String userEmail) {
}

