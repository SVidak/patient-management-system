package com.java.billingservice.dto.bill;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillRequestDTO {

    @NotBlank(message = "Patient ID is required")
    private String patientId;

    @NotBlank(message = "Treatments are required")
    private List<String> treatmentIds;

}

