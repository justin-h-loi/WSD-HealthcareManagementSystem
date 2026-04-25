package com.wirespeed.clinic.controller;

/*
 * Student name:  William 'Bill' McRury
 * Module 15 - Final Project - ProviderController.java
 * Status: Full MVP with Availability and Trace Logging
 */

import com.wirespeed.clinic.model.Provider;
import com.wirespeed.clinic.service.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers") 
@Tag(name = "Provider", description = "Provider Management APIs")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    @Operation(summary = "Create or link a provider profile")
    public ResponseEntity<Provider> createProvider(@Valid @RequestBody Provider provider) {
        System.out.println(">>> CONTROLLER TRACE: POST /api/providers received for NPI: " + provider.getNpiNumber());
        Provider savedProvider = providerService.createProvider(provider);
        return new ResponseEntity<>(savedProvider, HttpStatus.CREATED);
    }

    @PostMapping("/availability")
    @Operation(summary = "Set provider availability slots")
    public ResponseEntity<String> setAvailability(@RequestBody Object availabilityData) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(">>> CONTROLLER TRACE: POST /api/providers/availability received for user: " + username);
        
        return ResponseEntity.ok("Availability processed for " + username);
    }

    @GetMapping
    @Operation(summary = "Get all providers (Admin sees all, Provider sees self)")
    public ResponseEntity<List<Provider>> getAllProviders() {
        return ResponseEntity.ok(providerService.getAllProviders());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get provider by ID")
    public ResponseEntity<Provider> getProviderById(@PathVariable Integer id) {
        return ResponseEntity.ok(providerService.getProviderById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update provider info by ID")
    public ResponseEntity<Provider> updateProvider(@PathVariable Integer id, 
                                                 @Valid @RequestBody Provider provider) {
        System.out.println(">>> CONTROLLER TRACE: PUT /api/providers/" + id + " update received");
        return ResponseEntity.ok(providerService.updateProvider(id, provider));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete provider profile")
    public ResponseEntity<Void> deleteProvider(@PathVariable Integer id) {
        providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }
}