package com.wirespeed.clinic.controller;

import com.wirespeed.clinic.api.ProvidersApi;
import com.wirespeed.clinic.model.Provider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.List;
import java.util.ArrayList;

@RestController
public class ProviderController implements ProvidersApi {

    @Override
    public ResponseEntity<List<Provider>> providersGet() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<Void> providersPost(@Valid Provider provider) {
        // This is where you'll eventually validate the NPI number
        return ResponseEntity.status(201).build();
    }
}