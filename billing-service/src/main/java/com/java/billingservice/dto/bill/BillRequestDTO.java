package com.java.billingservice.dto.bill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank(message = "Treatment is required")
    @PositiveOrZero(message = "Amount must be positive or zero")
    private String amount;

    @Builder.Default
    private String status = "PENDING";

    @NotBlank
    private List<String> treatmentIds;

}

