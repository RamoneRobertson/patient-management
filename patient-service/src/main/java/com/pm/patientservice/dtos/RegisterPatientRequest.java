package com.pm.patientservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterPatientRequest {
  @NotBlank(message = "Name is required.")
  @Size(max = 100, message = "Name cannot exceed 100 characters.")
  private String name;

  @NotBlank(message = "Email is required.")
  @Email(message = "Must use a valid email address.")
  private String email;

  @NotBlank(message = "Password is required.")
  private String password;

  @NotBlank(message = "Address is required.")
  private String address;

  @NotBlank(message = "Date of Birth is required.")
  private String dateOfBirth;

  @NotBlank(message = "Registration date is required.")
  private String registeredDate;

}
