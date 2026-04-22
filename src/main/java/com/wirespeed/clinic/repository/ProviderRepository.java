package com.wirespeed.clinic.repository;

import com.wirespeed.clinic.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    boolean existsByNpiNumber(String npiNumber);
    Optional<Provider> findByNpiNumber(String npiNumber);
}
