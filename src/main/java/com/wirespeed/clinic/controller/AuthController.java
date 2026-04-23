package com.wirespeed.clinic.controller;

/*
 * Student name:  William 'Bill' McRury
 * EN.605.789.81.SP26 - Final Project
 */

import com.wirespeed.clinic.model.SignupRequest;
import com.wirespeed.clinic.security.JwtUtils;
import com.wirespeed.clinic.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth") 
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthService authService;

    /**
     * Handles user login and returns a JWT.
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest) {
        // 1. Authenticate using Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.get("username"), 
                        loginRequest.get("password")
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // 2. Generate Token
        String username = authentication.getName();
        String jwt = jwtUtils.generateJwtToken(username);
        
        return ResponseEntity.ok(Map.of(
                "accessToken", jwt, 
                "tokenType", "Bearer"
        ));
    }

    /**
     * Handles user registration by delegating to AuthService.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        // Delegation: Let the Service handle the DB, Roles, and Encoding
        authService.registerUser(signUpRequest);
        
        return ResponseEntity.ok(Map.of(
                "message", "User registered successfully!"
        ));
    }
}