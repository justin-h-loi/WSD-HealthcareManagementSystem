package com.wirespeed.clinic.security;

/*
 * Student name:  William 'Bill' McRury
 * JwtUtils.java - JWT Generation and Validation with Trace Logging
 */

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSecret = "WirespeedClinicConnectSecretKeyForJHUProject2026";
    private final int jwtExpirationMs = 86400000; // 24 hours

    private final Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    public String generateJwtToken(String username) {
        System.out.println(">>> JWT TRACE: Creating token for: " + username);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            System.out.println(">>> JWT TRACE: Token validated.");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println(">>> JWT ERROR: Validation failed: " + e.getMessage());
        }
        return false;
    }
}