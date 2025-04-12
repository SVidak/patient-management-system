package com.java.billingservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingAccountResponseDTO {

    private String id;
    private String patientId;
    private String creationDate;
    private String updateDate;

    //private List<BillDTO> bills;
}
