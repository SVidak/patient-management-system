package com.java.billingservice.dto.billing;

import com.java.billingservice.dto.bill.BillResponseDTO;
import lombok.*;

import java.util.List;

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

    private List<BillResponseDTO> bills;
}
