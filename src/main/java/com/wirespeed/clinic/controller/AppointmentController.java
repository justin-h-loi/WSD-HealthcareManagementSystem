package com.wirespeed.clinic.controller;

import com.wirespeed.clinic.api.AppointmentsApi;
import com.wirespeed.clinic.model.Appointment;
import com.wirespeed.clinic.model.AppointmentStatus;
import com.wirespeed.clinic.model.AppointmentsIdBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.List;
import java.util.ArrayList;

@RestController
public class AppointmentController implements AppointmentsApi {

    @Override
    public ResponseEntity<Void> appointmentsPost(@Valid Appointment appointment) {
        return ResponseEntity.status(201).build();
    }

    // Updated to match the interface: (Integer, AppointmentStatus)
    @Override
    public ResponseEntity<List<Appointment>> appointmentsGet(
        @Valid Integer patientId, 
        @Valid AppointmentStatus status
    ) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<Void> appointmentsIdPatch(
        Integer id, 
        @Valid AppointmentsIdBody appointmentsIdBody
    ) {
        return ResponseEntity.ok().build();
    }
}