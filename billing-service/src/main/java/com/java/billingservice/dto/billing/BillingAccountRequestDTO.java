package com.java.billingservice.dto.billing;

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

}
