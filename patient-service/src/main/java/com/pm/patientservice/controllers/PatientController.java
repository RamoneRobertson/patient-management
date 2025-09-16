package com.pm.patientservice.controllers;

import com.pm.patientservice.dtos.PatientDto;
import com.pm.patientservice.dtos.RegisterPatientRequest;
import com.pm.patientservice.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

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

  @GetMapping("/{userId}")
  public ResponseEntity<PatientDto> getPatient(@PathVariable UUID patientId){
    var patientDto = patientService.getPatient(patientId);
    return ResponseEntity.ok().body(patientDto);
  }

  @PostMapping
  public ResponseEntity<PatientDto> registerPatient(
          @Valid @RequestBody RegisterPatientRequest request,
          UriComponentsBuilder uriComponentsBuilder
  ) {
    var patientDto = patientService.registerPatient(request);
    var uri = uriComponentsBuilder.path("/patients/{patientId}").buildAndExpand(patientDto.getPatientId()).toUri();
    return ResponseEntity.created(uri).body(patientDto);
  }

  @PostMapping
  public ResponseEntity<PatientDto> updatePatient(){

  }
}
