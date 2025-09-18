package com.pm.patientservice.services;

import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.dtos.PatientRequestDTO;
import com.pm.patientservice.exceptions.EmailAlreadyExistsException;
import com.pm.patientservice.exceptions.PatientNotFoundException;
import com.pm.patientservice.mappers.PatientMapper;
import com.pm.patientservice.models.Patient;
import com.pm.patientservice.repositories.PatientRepository;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientService {
  private final PatientRepository patientRepository;
  private final PatientMapper patientMapper;

  public List<PatientResponseDto> getPatients() {
    List<Patient> patients = patientRepository.findAll();
    return patients.stream().map(patientMapper::toDto).toList();
  }

  public PatientResponseDto getPatient(UUID patientId) {
    var patient = patientRepository.findById(patientId).orElseThrow();
    return patientMapper.toDto(patient);
  }

  public PatientResponseDto registerPatient(PatientRequestDTO request) {
    if(patientRepository.existsByEmail(request.getEmail())) {
      throw new EmailAlreadyExistsException("A patient with this email already exists: "
              + request.getEmail());
    }
    var patient = patientMapper.toEntity(request);
    patientRepository.save(patient);
    return patientMapper.toDto(patient);
  }

  public PatientResponseDto updatePatient(
          UUID patientId,
          @Validated(Default.class) PatientRequestDTO request ) {
    var patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + patientId));
    if(patientRepository.existsByEmail(request.getEmail())) {
      throw new EmailAlreadyExistsException("A patient with this email already exists: "
              + request.getEmail());
    }
    patientMapper.updatePatient(patient, request);
    patientRepository.save(patient);
    return patientMapper.toDto(patient);
  }

  public void deletePatient(UUID patientId) {
    var patient = patientRepository.findById(patientId).orElseThrow();
    patientRepository.delete(patient);
  }
}
