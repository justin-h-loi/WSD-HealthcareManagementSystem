package com.wirespeed.clinic.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-04-19T00:09:17.180339900-04:00[America/New_York]", comments = "Generator version: 7.4.0")
@Entity
@Table(name = "providers")
public class Provider {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "npi_number", nullable = false, unique = true)
  private String npiNumber;

  private String specialty;

  public Provider() {
    super();
  }

  public Provider(String firstName, String lastName, String npiNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.npiNumber = npiNumber;
  }

  public Provider id(Integer id) {
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

  public Provider firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  @NotNull 
  @Schema(name = "firstName", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Provider lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  @NotNull 
  @Schema(name = "lastName", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Provider npiNumber(String npiNumber) {
    this.npiNumber = npiNumber;
    return this;
  }

  @NotNull 
  @Schema(name = "npiNumber", description = "Required for external CMS validation", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("npiNumber")
  public String getNpiNumber() {
    return npiNumber;
  }
  public void setNpiNumber(String npiNumber) {
    this.npiNumber = npiNumber;
  }

  public Provider specialty(String specialty) {
    this.specialty = specialty;
    return this;
  }
  
  @Schema(name = "specialty", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("specialty")
  public String getSpecialty() {
    return specialty;
  }
  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Provider provider = (Provider) obj;
    return Objects.equals(this.id, provider.id) &&
        Objects.equals(this.firstName, provider.firstName) &&
        Objects.equals(this.lastName, provider.lastName) &&
        Objects.equals(this.npiNumber, provider.npiNumber) &&
        Objects.equals(this.specialty, provider.specialty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, npiNumber, specialty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Provider {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    npiNumber: ").append(toIndentedString(npiNumber)).append("\n");
    sb.append("    specialty: ").append(toIndentedString(specialty)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object obj) {
    if (obj == null) {
      return "null";
    }
    return obj.toString().replace("\n", "\n    ");
  }
}
