package com.wirespeed.clinic.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-04-19T00:09:17.180339900-04:00[America/New_York]", comments = "Generator version: 7.4.0")
public enum AppointmentStatus {
  SCHEDULED("SCHEDULED"),
  CHECKED_IN("CHECKED_IN"),
  IN_PROGRESS("IN_PROGRESS"),
  COMPLETED("COMPLETED"),
  CANCELLED("CANCELLED");

  private String value;

  AppointmentStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AppointmentStatus fromValue(String value) {
    for (AppointmentStatus b : AppointmentStatus.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

