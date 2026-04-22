package com.wirespeed.clinic.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-04-19T00:09:17.180339900-04:00[America/New_York]", comments = "Generator version: 7.4.0")
@Entity
@Table(name = "appointments")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "patient_id", nullable = false)
  private Integer patientId;

  @Column(name = "provider_id", nullable = false)
  private Integer providerId;

  @Column(name = "appointment_date", nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime appointmentDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AppointmentStatus status;

  @Column(columnDefinition = "TEXT")
  private String notes;

  public Appointment() {
    super();
    this.status = AppointmentStatus.SCHEDULED;
  }

  public Appointment(Integer patientId, Integer providerId, OffsetDateTime appointmentDate) {
    this.patientId = patientId;
    this.providerId = providerId;
    this.appointmentDate = appointmentDate;
  }

  public Appointment id(Integer id) {
    this.id = id;
    return this;
  }
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  public Appointment patientId(Integer patientId) {
    this.patientId = patientId;
    return this;
  }

  @NotNull 
  @Schema(name = "patientId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("patientId")
  public Integer getPatientId() {
    return patientId;
  }
  public void setPatientId(Integer patientId) {
    this.patientId = patientId;
  }

  public Appointment providerId(Integer providerId) {
    this.providerId = providerId;
    return this;
  }

  @NotNull 
  @Schema(name = "providerId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("providerId")
  public Integer getProviderId() {
    return providerId;
  }
  public void setProviderId(Integer providerId) {
    this.providerId = providerId;
  }

  public Appointment appointmentDate(OffsetDateTime appointmentDate) {
    this.appointmentDate = appointmentDate;
    return this;
  }

  @NotNull @Valid 
  @Schema(name = "appointmentDate", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("appointmentDate")
  public OffsetDateTime getAppointmentDate() {
    return appointmentDate;
  }
  public void setAppointmentDate(OffsetDateTime appointmentDate) {
    this.appointmentDate = appointmentDate;
  }

  public Appointment status(AppointmentStatus status) {
    this.status = status;
    return this;
  }

  @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public AppointmentStatus getStatus() {
    return status;
  }
  public void setStatus(AppointmentStatus status) {
    this.status = status;
  }

  public Appointment notes(String notes) {
    this.notes = notes;
    return this;
  }
  
  @Schema(name = "notes", description = "Clinical documentation added by provider", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("notes")
  public String getNotes() {
    return notes;
  }
  public void setNotes(String notes) {
    this.notes = notes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Appointment appointment = (Appointment) o;
    return Objects.equals(this.id, appointment.id) &&
        Objects.equals(this.patientId, appointment.patientId) &&
        Objects.equals(this.providerId, appointment.providerId) &&
        Objects.equals(this.appointmentDate, appointment.appointmentDate) &&
        Objects.equals(this.status, appointment.status) &&
        Objects.equals(this.notes, appointment.notes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, patientId, providerId, appointmentDate, status, notes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Appointment {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    patientId: ").append(toIndentedString(patientId)).append("\n");
    sb.append("    providerId: ").append(toIndentedString(providerId)).append("\n");
    sb.append("    appointmentDate: ").append(toIndentedString(appointmentDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
