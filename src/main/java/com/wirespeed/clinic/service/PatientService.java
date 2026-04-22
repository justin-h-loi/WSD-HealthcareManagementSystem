package com.wirespeed.clinic.service;

import com.wirespeed.clinic.model.Patient;
import com.wirespeed.clinic.repository.AppointmentRepository;
import com.wirespeed.clinic.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientService(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Integer id) {
        return findPatientById(id);
    }

    public Patient createPatient(Patient patient) {
        validateForSave(patient, null);
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Integer id, Patient patient) {
        Patient existingPatient = findPatientById(id);
        validateForSave(patient, id);

        existingPatient.setFirstName(patient.getFirstName());
        existingPatient.setLastName(patient.getLastName());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setAddress(patient.getAddress());
        existingPatient.setCity(patient.getCity());
        existingPatient.setZipCode(patient.getZipCode());

        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Integer id) {
        Patient patient = findPatientById(id);
        if (appointmentRepository.existsByPatientId(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cannot delete patient with existing appointments");
        }
        patientRepository.delete(patient);
    }

    public Patient findPatientById(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Patient with id " + id + " not found"));
    }

    private void validateForSave(Patient patient, Integer currentPatientId) {
        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient body is required");
        }
        if (patient.getFirstName() == null || patient.getFirstName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name is required");
        }
        if (patient.getLastName() == null || patient.getLastName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name is required");
        }
        if (patient.getEmail() == null || patient.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }
        Patient existingPatient = patientRepository.findByEmail(patient.getEmail()).orElse(null);
        if (existingPatient != null && !existingPatient.getId().equals(currentPatientId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Patient with email '" + patient.getEmail() + "' already exists");
        }
    }
}
