package com.java.billingservice.mapper.item;

import com.java.billingservice.dto.item.BillItemResponseDTO;
import com.java.billingservice.model.BillItem;

public class BillItemMapper {

    public static BillItemResponseDTO toBillItemResponseDTO(BillItem billItem) {
        return BillItemResponseDTO.builder()
                .id(billItem.getId().toString())
                .treatmentId(billItem.getTreatmentId())
                .name(billItem.getName())
                .description(billItem.getDescription())
                .price(billItem.getPrice().toString())
                .billId(billItem.getId().toString())
                .build();
    }

    public static BillItem toBillItem(BillItemResponseDTO billItemResponseDTO) {
        return BillItem.builder()
                .treatmentId(billItemResponseDTO.getTreatmentId())
                .name(billItemResponseDTO.getName())
                .description(billItemResponseDTO.getDescription())
                .price(Long.valueOf(billItemResponseDTO.getPrice()))
                .build();
    }
}
