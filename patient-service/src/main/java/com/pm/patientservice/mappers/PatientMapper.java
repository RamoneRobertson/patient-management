package com.pm.patientservice.mappers;

import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.dtos.PatientRequestDTO;
import com.pm.patientservice.models.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper {
  @Mapping(target = "patientId", source = "id")
  PatientResponseDto toDto(Patient patient);
  Patient toEntity(PatientRequestDTO request);
  Patient toEntity(PatientResponseDto patientResponseDto);
  void updatePatient(@MappingTarget Patient patient,  PatientRequestDTO request);
}
