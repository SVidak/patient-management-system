package com.java.treatmentservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentResponseDTO {

    private String id;

    private String name;
    private String description;
    private String duration;
    private String price;
}
