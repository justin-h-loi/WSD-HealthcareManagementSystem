package com.wirespeed.clinic.controller;

import com.wirespeed.clinic.api.AuthApi;
import com.wirespeed.clinic.model.AuthResponse;
import com.wirespeed.clinic.model.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
public class AuthController implements AuthApi {

    @Override
    public ResponseEntity<AuthResponse> authLoginPost(@Valid LoginRequest loginRequest) {
        AuthResponse response = new AuthResponse();
        response.setToken("dummy-token-for-wirespeed-demons");
        return ResponseEntity.ok(response);
    }
}