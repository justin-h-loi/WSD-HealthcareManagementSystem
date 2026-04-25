package com.wirespeed.clinic.repository;

/*
 * Student name:  William 'Bill' McRury
 */

import com.wirespeed.clinic.model.Provider;
import com.wirespeed.clinic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    
    /**
     * Used by ProviderService to prevent duplicate clinical 
     * profiles for the same authenticated user.
     */
    boolean existsByUser(User user);

    /**
     * Navigates the @OneToOne relationship: Provider -> User -> Username.
     * Essential for the 'getAllProviders' role-based logic.
     */
    Optional<Provider> findByUserUsername(String username);

    /**
     * Checks uniqueness against the federal NPI field.
     */
    boolean existsByNpiNumber(String npiNumber);
}