package com.wirespeed.clinic.repository;

import com.wirespeed.clinic.model.Appointment;
import com.wirespeed.clinic.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByProviderId(Integer providerId);

    List<Appointment> findByProviderIdAndStatus(Integer providerId, AppointmentStatus status);

    List<Appointment> findByStatus(AppointmentStatus status);

    List<Appointment> findByPatientId(Integer patientId);

    boolean existsByPatientId(Integer patientId);

    boolean existsByProviderId(Integer providerId);
}
