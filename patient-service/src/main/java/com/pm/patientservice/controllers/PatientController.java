package com.pm.patientservice.controllers;

import com.pm.patientservice.dtos.PatientDto;
import com.pm.patientservice.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
  private final PatientService patientService;

  public PatientController(PatientService patientService){
    this.patientService = patientService;
  }

  @GetMapping
  public ResponseEntity<List<PatientDto>> getPatients() {
    var patients = patientService.getPatients();
    return ResponseEntity.ok().body(patients);
  }
}
