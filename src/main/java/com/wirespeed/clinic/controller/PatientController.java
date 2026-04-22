package com.wirespeed.clinic.controller;

import com.wirespeed.clinic.api.PatientsApi;
import com.wirespeed.clinic.model.Appointment;
import com.wirespeed.clinic.model.Patient;
import com.wirespeed.clinic.service.AppointmentService;
import com.wirespeed.clinic.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController implements PatientsApi {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    public PatientController(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    /**
     * GET /patients
     * Returns all patient records.
     */
    @Override
    public ResponseEntity<List<Patient>> patientsGet() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    /**
     * GET /patients/{id}
     * Returns a single patient by id.
     */
    @Override
    public ResponseEntity<Patient> patientsIdGet(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    /**
     * POST /patients
     * Creates a new patient profile. Returns 201 Created on success.
     */
    @Override
    public ResponseEntity<Void> patientsPost(@Valid Patient patient) {
        patientService.createPatient(patient);
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<Void> patientsIdPut(@PathVariable("id") Integer id, @Valid Patient patient) {
        patientService.updatePatient(id, patient);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> patientsIdDelete(@PathVariable("id") Integer id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /patients/{id}/appointments
     * Returns the appointment history for a specific patient.
     */
    @Override
    public ResponseEntity<List<Appointment>> patientsIdAppointmentsGet(@PathVariable("id") Integer id) {
        List<Appointment> appointments = appointmentService.getAppointmentsForPatient(id);
        return ResponseEntity.ok(appointments);
    }
}
