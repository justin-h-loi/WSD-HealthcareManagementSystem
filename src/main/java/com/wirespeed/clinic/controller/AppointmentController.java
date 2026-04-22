package com.wirespeed.clinic.controller;

import com.wirespeed.clinic.api.AppointmentsApi;
import com.wirespeed.clinic.model.Appointment;
import com.wirespeed.clinic.model.AppointmentStatus;
import com.wirespeed.clinic.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppointmentController implements AppointmentsApi {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * GET /appointments?providerId={id}&status={status}
     * Returns appointments, optionally filtered by providerId and/or status.
     */
    @Override
    public ResponseEntity<List<Appointment>> appointmentsGet(
            @RequestParam(value = "providerId", required = false) @Valid Integer providerId,
            @RequestParam(value = "status", required = false) @Valid AppointmentStatus status) {
        return ResponseEntity.ok(appointmentService.getAppointments(providerId, status));
    }

    /**
     * GET /appointments/{id}
     * Returns a single appointment by id.
     */
    @Override
    public ResponseEntity<Appointment> appointmentsIdGet(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    /**
     * POST /appointments
     * Schedules a new appointment. Validates patient and provider exist.
     * Default status is SCHEDULED.
     */
    @Override
    public ResponseEntity<Void> appointmentsPost(@Valid Appointment appointment) {
        appointmentService.createAppointment(appointment);
        return ResponseEntity.status(201).build();
    }

    /**
     * PUT /appointments/{id}
     * Updates an existing appointment.
     */
    @Override
    public ResponseEntity<Void> appointmentsIdPut(@PathVariable("id") Integer id, @Valid Appointment appointment) {
        appointmentService.updateAppointment(id, appointment);
        return ResponseEntity.ok().build();
    }

    /**
     * DELETE /appointments/{id}
     * Deletes an appointment by id.
     */
    @Override
    public ResponseEntity<Void> appointmentsIdDelete(@PathVariable("id") Integer id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
