package com.wirespeed.clinic.service;

/*
 * Student name:  William 'Bill' McRury
 * Module 15 - Final Project - ProviderService.java
 * EN.605.789.81.SP26
 * Full MVP Version with Ownership Lockdown and helper methods for AppointmentService.
 */

import com.wirespeed.clinic.model.Provider;
import com.wirespeed.clinic.model.User;
import com.wirespeed.clinic.repository.ProviderRepository;
import com.wirespeed.clinic.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;
    private final NpiRegistryService npiRegistryService;

    public ProviderService(ProviderRepository providerRepository, 
                           UserRepository userRepository, 
                           NpiRegistryService npiRegistryService) {
        this.providerRepository = providerRepository;
        this.userRepository = userRepository;
        this.npiRegistryService = npiRegistryService;
    }

    /**
     * POST /api/providers
     * Links the current authenticated user to a new Federal NPI record.
     */
    public Provider createProvider(Provider provider) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        System.out.println(">>> SERVICE TRACE: Creating provider for user: " + auth.getName());

        if (providerRepository.existsByUser(currentUser)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already has an associated Provider record.");
        }

        // NPI Registry validation check
        boolean isValid = npiRegistryService.validateNpi(
            provider.getNpiNumber(), 
            provider.getFirstName(), 
            provider.getLastName()
        );

        if (!isValid) {
            System.err.println(">>> SERVICE ERROR: NPI Validation failed for " + provider.getNpiNumber());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Federal NPI validation failed.");
        }

        provider.setUser(currentUser);
        return providerRepository.save(provider);
    }

    /**
     * Requirement for AppointmentService: Internal helper to find provider.
     */
    public Provider findProviderById(Integer id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Provider not found with ID: " + id));
    }

    @PreAuthorize("@securityService.isProviderOwner(authentication, #id)")
    public Provider getProviderById(Integer id) {
        return findProviderById(id);
    }

    @PreAuthorize("@securityService.isProviderOwner(authentication, #id)")
    public Provider updateProvider(Integer id, Provider provider) {
        Provider existing = findProviderById(id);
        
        existing.setFirstName(provider.getFirstName());
        existing.setLastName(provider.getLastName());
        existing.setSpecialty(provider.getSpecialty());
        
        return providerRepository.save(existing);
    }

    @PreAuthorize("@securityService.isProviderOwner(authentication, #id) or hasRole('ADMIN')")
    public void deleteProvider(Integer id) {
        Provider provider = findProviderById(id);
        providerRepository.delete(provider);
    }

    public List<Provider> getAllProviders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return providerRepository.findAll();
        }
        // Fix: Uses the repository method that navigates through the User object
        return providerRepository.findByUserUsername(auth.getName()).map(List::of).orElse(List.of());
    }
}