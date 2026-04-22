package com.wirespeed.clinic.repository;

import com.wirespeed.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    boolean existsByEmail(String email);
    Optional<Patient> findByEmail(String email);
}
