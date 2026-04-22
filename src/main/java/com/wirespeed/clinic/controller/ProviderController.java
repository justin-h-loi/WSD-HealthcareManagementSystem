package com.wirespeed.clinic.controller;

import com.wirespeed.clinic.api.ProvidersApi;
import com.wirespeed.clinic.model.Provider;
import com.wirespeed.clinic.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderController implements ProvidersApi {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    /**
     * GET /providers
     * Returns all registered healthcare providers.
     */
    @Override
    public ResponseEntity<List<Provider>> providersGet() {
        return ResponseEntity.ok(providerService.getAllProviders());
    }

    /**
     * GET /providers/{id}
     * Returns a single provider by id.
     */
    @Override
    public ResponseEntity<Provider> providersIdGet(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(providerService.getProviderById(id));
    }

    /**
     * POST /providers
     * Registers a new provider. NPI format is validated locally (10-digit numeric).
     * External NPI validation needs to be added.
     * Returns 201 Created on success, 422 Unprocessable Entity if NPI is invalid.
     */
    @Override
    public ResponseEntity<Void> providersPost(@Valid Provider provider) {
        providerService.createProvider(provider);
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<Void> providersIdPut(@PathVariable("id") Integer id, @Valid Provider provider) {
        providerService.updateProvider(id, provider);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> providersIdDelete(@PathVariable("id") Integer id) {
        providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }
}
