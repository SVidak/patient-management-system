package com.java.billingservice.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillItemRequestDTO {

    @NotBlank
    private String treatmentId;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @PositiveOrZero
    private String price;
}
