package com.g1elproyectegrande.service.auth;

import com.g1elproyectegrande.config.auth.*;

import com.g1elproyectegrande.entity.auth.RefreshToken;
import com.g1elproyectegrande.entity.auth.User;
import com.g1elproyectegrande.repository.auth.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenService {

    private AuthConfigProperties authConfigProperties;
    private RefreshTokenRepository refreshTokenRepository;


    public JwtTokenService(AuthConfigProperties authConfigProperties, RefreshTokenRepository refreshTokenRepository) {
        this.authConfigProperties = authConfigProperties;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String generateToken(String username, LocalDateTime expirationTime) {
        var currentTime = LocalDateTime.now();
//        var expirationTime = currentTime.plusMinutes(authConfigProperties.validity());
//        var expirationTime = currentTime.plusMinutes(authConfigProperties.refreshTokenValidity());


        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(transformLDTToDate(currentTime))
                .setExpiration(transformLDTToDate(expirationTime))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public Date getExpirationTimeFromToken(String token) {
        return getClaims(token).getExpiration();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        boolean isExpired = getExpirationTimeFromToken(token).before(new Date());

        return username.equals(userDetails.getUsername()) && !isExpired;
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Date transformLDTToDate(LocalDateTime currentTime) {
        return Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(authConfigProperties.secret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //--------------------------------------------------------------------------------------------------


    public String generateAccessToken(String username) {
        // Generate an access token with a shorter validity period
        var currentTime = LocalDateTime.now();
        var expirationTime = currentTime.plusMinutes(authConfigProperties.accessTokenValidity());
        return generateToken(username, expirationTime);
    }

    public String generateRefreshToken(User user) {
        // Generate a refresh token with a longer validity period
        var currentTime = LocalDateTime.now();
        var expirationTime = currentTime.plusMinutes(authConfigProperties.refreshTokenValidity());
        String refreshTokenValue = generateToken(user.getEmail(), expirationTime);

        // Save the refresh token in the database
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(refreshTokenValue);
        refreshToken.setExpirationTime(expirationTime);
        refreshToken.setUser(user);
        refreshTokenRepository.save(refreshToken);

        return refreshTokenValue;
    }

    public boolean validateRefreshToken(String token) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);

        if (refreshToken.isPresent()) {
            LocalDateTime expirationTime = refreshToken.get().getExpirationTime();
            return !expirationTime.isBefore(LocalDateTime.now());
        }

        return false;
    }


}

