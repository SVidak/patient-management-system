package com.java.treatmentservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentRequestDTO {

    @NotBlank
    private String name;
    private String description;
    private String price;
    private String duration;
}
