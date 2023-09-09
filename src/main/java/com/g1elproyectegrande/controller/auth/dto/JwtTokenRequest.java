package com.g1elproyectegrande.controller.auth.dto;

//import jakarta.validation.constraints.NotBlank;

import javax.validation.constraints.NotBlank;

public record JwtTokenRequest(
        @NotBlank(message = "username cannot be blank")
        String username,
        @NotBlank(message = "password cannot be blank")
        String password
) {
}
