package com.g1elproyectegrande.service.auth;

import com.g1elproyectegrande.config.auth.*;

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

@Service
public class JwtTokenService {

    private AuthConfigProperties authConfigProperties;

    public JwtTokenService(AuthConfigProperties authConfigProperties) {
        this.authConfigProperties = authConfigProperties;
    }

    public String generateToken(String username) {
        var currentTime = LocalDateTime.now();
        var expirationTime = currentTime.plusMinutes(authConfigProperties.validity());

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
}
