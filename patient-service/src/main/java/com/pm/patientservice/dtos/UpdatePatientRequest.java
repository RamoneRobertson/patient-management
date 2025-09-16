package com.pm.patientservice.dtos;

import lombok.Data;

@Data
public class UpdatePatientRequest {
  private String name;
  private String email;
  private String address;
  private String dateOfBirth;
}
