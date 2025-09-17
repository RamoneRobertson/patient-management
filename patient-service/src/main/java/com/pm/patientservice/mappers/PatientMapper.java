package com.pm.patientservice.mappers;

import com.pm.patientservice.dtos.PatientDto;
import com.pm.patientservice.dtos.RegisterPatientRequest;
import com.pm.patientservice.dtos.UpdatePatientRequest;
import com.pm.patientservice.models.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PatientMapper {
  @Mapping(target = "patientId", source = "id")
  PatientDto toDto(Patient patient);
  Patient toEntity(RegisterPatientRequest request);
  Patient toEntity(PatientDto patientDto);
  void updatePatient(@MappingTarget Patient patient, UpdatePatientRequest request);
}
