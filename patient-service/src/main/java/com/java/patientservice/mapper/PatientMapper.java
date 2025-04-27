package com.java.patientservice.mapper;

import com.java.patientservice.dto.PatientRequestDTO;
import com.java.patientservice.dto.PatientResponseDTO;
import com.java.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toPatientResponseDTO(Patient patient) {
        return PatientResponseDTO.builder()
                .id(patient.getId().toString())
                .name(patient.getName())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .birthDate(patient.getBirthDate().toString())
                .build();
    }

    public static Patient toPatient(PatientRequestDTO patientRequestDTO) {
        return Patient.builder()
                .name(patientRequestDTO.getName())
                .email(patientRequestDTO.getEmail())
                .address(patientRequestDTO.getAddress())
                .birthDate(LocalDate.parse(patientRequestDTO.getBirthDate()))
                .registeredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()))
                .build();
    }
}
