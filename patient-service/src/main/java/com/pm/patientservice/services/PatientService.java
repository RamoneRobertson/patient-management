package com.pm.patientservice.services;

import com.pm.patientservice.dtos.PatientDto;
import com.pm.patientservice.mappers.PatientMapper;
import com.pm.patientservice.models.Patient;
import com.pm.patientservice.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
  private final PatientRepository patientRepository;
  private final PatientMapper patientMapper;

  public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
    this.patientRepository = patientRepository;
    this.patientMapper = patientMapper;
  }

  public List<PatientDto> getPatients() {
    List<Patient> patients = patientRepository.findAll();
    return patients.stream().map(patientMapper::toDto).toList();
  }
}
