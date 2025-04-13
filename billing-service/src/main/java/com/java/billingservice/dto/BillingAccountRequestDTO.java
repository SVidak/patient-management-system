package com.java.billingservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingAccountRequestDTO {

    @NotBlank(message = "Patient ID is required")
    private String patientId;

//    @NotBlank(message = "Creation date is required")
//    private String creationDate;
//
//    @NotBlank(message = "Update date is required")
//    private String lastUpdateDate;
}
