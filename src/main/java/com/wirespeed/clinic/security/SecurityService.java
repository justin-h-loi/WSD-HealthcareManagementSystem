package com.wirespeed.clinic.security;

import com.wirespeed.clinic.repository.ProviderRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Student name:  William 'Bill' McRury
 * SecurityService.java - Principal-to-Resource Mapping
 * * This service is called by @PreAuthorize annotations to ensure 
 * that Providers can only touch their own data.
 */
@Service("securityService")
public class SecurityService {

    private final ProviderRepository providerRepository;

    public SecurityService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public boolean isProviderOwner(Authentication authentication, Integer providerId) {
        // Rule 1: Admins can see everything
        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }

        // Rule 2: Providers can only see records linked to their username
        return providerRepository.findById(providerId)
                .map(provider -> {
                    String ownerUsername = provider.getUser().getUsername();
                    return ownerUsername.equalsIgnoreCase(authentication.getName());
                })
                .orElse(false);
    }
}