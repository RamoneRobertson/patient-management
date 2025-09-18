package com.pm.patientservice.controllers;

import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.dtos.PatientRequestDTO;
import com.pm.patientservice.dtos.validators.CreatePatientValidationGroup;
import com.pm.patientservice.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Patient", description = "API for managing patients")
public class PatientController {
  private final PatientService patientService;

  public PatientController(PatientService patientService){
    this.patientService = patientService;
  }

  @GetMapping
  @Operation(summary = "Get all patients")
  public ResponseEntity<List<PatientResponseDto>> getPatients() {
    var patients = patientService.getPatients();
    return ResponseEntity.ok().body(patients);
  }

  @GetMapping("/{patientId}")
  @Operation(summary = "Get a single patient")
  public ResponseEntity<PatientResponseDto> getPatient(@PathVariable UUID patientId){
    var patientDto = patientService.getPatient(patientId);
    return ResponseEntity.ok().body(patientDto);
  }

  @PostMapping
  @Operation(summary = "Create a new patient")
  public ResponseEntity<PatientResponseDto> registerPatient(
          @Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO request,
          UriComponentsBuilder uriComponentsBuilder
  ) {
    var patientDto = patientService.registerPatient(request);
    var uri = uriComponentsBuilder.path("/patients/{patientId}").buildAndExpand(patientDto.getPatientId()).toUri();
    return ResponseEntity.created(uri).body(patientDto);
  }

  @PutMapping("/{patientId}")
  @Operation(summary = "Update a patient")
  public ResponseEntity<PatientResponseDto> updatePatient(
          @PathVariable UUID patientId,
          @Validated({Default.class}) @RequestBody PatientRequestDTO request
  ) {
    var patientDto = patientService.updatePatient(patientId, request);
    return ResponseEntity.ok().body(patientDto);
  }

  @DeleteMapping("/{patientId}")
  @Operation(summary = "Delete a patient")
  public ResponseEntity<Void> deletePatient(@PathVariable UUID patientId){
    patientService.deletePatient(patientId);
    return ResponseEntity.noContent().build();
  }
}
