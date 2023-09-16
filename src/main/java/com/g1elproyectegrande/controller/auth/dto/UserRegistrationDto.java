package com.g1elproyectegrande.controller.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationDto(
        @NotBlank(message = "email cannot be empty")
        String email,
        @Size(min = 10, message = "Password must be at least 10 chars")
        String password
) {
}
