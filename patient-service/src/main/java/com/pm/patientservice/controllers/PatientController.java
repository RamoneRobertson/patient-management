package com.pm.patientservice.controllers;

import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.dtos.PatientRequestDTO;
import com.pm.patientservice.dtos.validators.CreatePatientValidationGroup;
import com.pm.patientservice.services.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
  public ResponseEntity<List<PatientResponseDto>> getPatients() {
    var patients = patientService.getPatients();
    return ResponseEntity.ok().body(patients);
  }

  @GetMapping("/{patientId}")
  public ResponseEntity<PatientResponseDto> getPatient(@PathVariable UUID patientId){
    var patientDto = patientService.getPatient(patientId);
    return ResponseEntity.ok().body(patientDto);
  }

  @PostMapping
  public ResponseEntity<PatientResponseDto> registerPatient(
          @Valid @RequestBody PatientRequestDTO request,
          UriComponentsBuilder uriComponentsBuilder
  ) {
    var patientDto = patientService.registerPatient(request);
    var uri = uriComponentsBuilder.path("/patients/{patientId}").buildAndExpand(patientDto.getPatientId()).toUri();
    return ResponseEntity.created(uri).body(patientDto);
  }

  @PutMapping("/{patientId}")
  public ResponseEntity<PatientResponseDto> updatePatient(
          @PathVariable UUID patientId,
          @Valid @RequestBody PatientRequestDTO request
  ) {
    var patientDto = patientService.updatePatient(patientId, request);
    return ResponseEntity.ok().body(patientDto);
  }

  @DeleteMapping("/{patientId}")
  public ResponseEntity<Void> deletePatient(@PathVariable UUID patientId){
    patientService.deletePatient(patientId);
    return ResponseEntity.noContent().build();
  }
}
