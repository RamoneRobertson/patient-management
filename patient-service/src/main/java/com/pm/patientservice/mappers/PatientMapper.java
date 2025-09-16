package com.pm.patientservice.mappers;

import com.pm.patientservice.dtos.PatientDto;
import com.pm.patientservice.models.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
  PatientDto toDto(Patient patient);
}
