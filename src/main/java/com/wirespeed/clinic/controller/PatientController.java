package com.wirespeed.clinic.controller;

import com.wirespeed.clinic.api.PatientsApi;
import com.wirespeed.clinic.model.Patient;
import com.wirespeed.clinic.model.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.List;
import java.util.ArrayList;

@RestController
public class PatientController implements PatientsApi {

    @Override
    public ResponseEntity<List<Patient>> patientsGet() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<Void> patientsPost(@Valid Patient patient) {
        return ResponseEntity.status(201).build();
    }

    // Adding the missing method required by the PatientsApi interface
    @Override
    public ResponseEntity<List<Appointment>> patientsIdAppointmentsGet(Integer id) {
        // Returning an empty list for now to satisfy the compiler
        return ResponseEntity.ok(new ArrayList<>());
    }
}