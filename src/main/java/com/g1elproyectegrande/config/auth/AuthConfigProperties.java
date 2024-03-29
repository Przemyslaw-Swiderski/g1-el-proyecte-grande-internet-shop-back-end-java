package com.g1elproyectegrande.config.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = "app.jwt")
//public record AuthConfigProperties(String secret, int validity) {
//}

@ConfigurationProperties(prefix = "app.jwt")
public record AuthConfigProperties(String secret, int accessTokenValidity, int refreshTokenValidity) {
    // Constructors and getters
}