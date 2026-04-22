package com.wirespeed.clinic.service;

import com.wirespeed.clinic.model.Appointment;
import com.wirespeed.clinic.model.AppointmentStatus;
import com.wirespeed.clinic.repository.AppointmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final ProviderService providerService;

    public AppointmentService(AppointmentRepository appointmentRepository,
                            PatientService patientService,
                            ProviderService providerService) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.providerService = providerService;
    }

    public List<Appointment> getAppointments(Integer providerId, AppointmentStatus status) {
        if (providerId != null && status != null) {
            return appointmentRepository.findByProviderIdAndStatus(providerId, status);
        } else if (providerId != null) {
            return appointmentRepository.findByProviderId(providerId);
        } else if (status != null) {
            return appointmentRepository.findByStatus(status);
        } else {
            return appointmentRepository.findAll();
        }
    }

    public Appointment getAppointmentById(Integer id) {
        return findAppointmentById(id);
    }

    public Appointment createAppointment(Appointment appointment) {
        validateForSave(appointment);

        patientService.findPatientById(appointment.getPatientId());
        providerService.findProviderById(appointment.getProviderId());
        if (appointment.getStatus() == null) {
            appointment.setStatus(AppointmentStatus.SCHEDULED);
        }
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Integer id, Appointment appointment) {
        Appointment entity = findAppointmentById(id);
        validateForSave(appointment);

        patientService.findPatientById(appointment.getPatientId());
        providerService.findProviderById(appointment.getProviderId());

        entity.setPatientId(appointment.getPatientId());
        entity.setProviderId(appointment.getProviderId());
        entity.setAppointmentDate(appointment.getAppointmentDate());
        entity.setStatus(appointment.getStatus() != null ? appointment.getStatus() : entity.getStatus());
        if (entity.getStatus() == null) {
            entity.setStatus(AppointmentStatus.SCHEDULED);
        }
        entity.setNotes(appointment.getNotes());

        return appointmentRepository.save(entity);
    }

    public void deleteAppointment(Integer id) {
        Appointment appointment = findAppointmentById(id);
        appointmentRepository.delete(appointment);
    }

    public List<Appointment> getAppointmentsForPatient(Integer patientId) {
        patientService.findPatientById(patientId);
        return appointmentRepository.findByPatientId(patientId);
    }

    private Appointment findAppointmentById(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Appointment with id " + id + " not found"));
    }

    private void validateForSave(Appointment appointment) {
        if (appointment == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Appointment body is required");
        }
        if (appointment.getPatientId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "patientId is required");
        }
        if (appointment.getProviderId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "providerId is required");
        }
        if (appointment.getAppointmentDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "appointmentDate is required");
        }
    }
}
