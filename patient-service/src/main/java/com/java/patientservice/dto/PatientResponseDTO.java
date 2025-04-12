package com.java.patientservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponseDTO {

    private String id;
    private String name;
    private String email;
    private String address;
    private String birthDate;
}
