package com.pm.patientservice.services;

import com.pm.patientservice.dtos.PatientDto;
import com.pm.patientservice.dtos.RegisterPatientRequest;
import com.pm.patientservice.dtos.UpdatePatientRequest;
import com.pm.patientservice.mappers.PatientMapper;
import com.pm.patientservice.models.Patient;
import com.pm.patientservice.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientService {
  private final PatientRepository patientRepository;
  private final PatientMapper patientMapper;

  public List<PatientDto> getPatients() {
    List<Patient> patients = patientRepository.findAll();
    return patients.stream().map(patientMapper::toDto).toList();
  }

  public PatientDto getPatient(UUID patientId) {
    var patient = patientRepository.findById(patientId).orElseThrow();
    return patientMapper.toDto(patient);
  }

  public PatientDto registerPatient(RegisterPatientRequest request){
    var patient = patientMapper.toEntity(request);
    patientRepository.save(patient);
    return patientMapper.toDto(patient);
  }

  public PatientDto updatePatient(UUID patientId, UpdatePatientRequest request) {
    var patient = patientRepository.findById(patientId).orElseThrow();
    patientMapper.updatePatient(patient, request);
    patientRepository.save(patient);
    return patientMapper.toDto(patient);
  }

  public void deletePatient(UUID patientId) {
    var patient = patientRepository.findById(patientId).orElseThrow();
    patientRepository.delete(patient);
  }
}
