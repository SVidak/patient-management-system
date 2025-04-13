package com.java.billingservice.dto.bill;

import com.java.billingservice.dto.item.BillItemResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillResponseDTO {

    private String id;
    private String patientId;
    private String createdDate;
    private String payedDate;
    private String status;
    private String amount;

    private String billingAccountId;

    private List<BillItemResponseDTO> billItems;
}
