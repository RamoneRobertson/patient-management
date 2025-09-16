package com.pm.patientservice.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PatientDto {
  private String id;
  private String name;
  private String email;
  private String address;
  private LocalDate dateOfBirth;
}
