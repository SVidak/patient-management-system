package com.java.billingservice.dto.item;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillItemResponseDTO {

    private String id;
    private String treatmentId;
    private String name;
    private String description;
    private String price;
    private String billId;
}
