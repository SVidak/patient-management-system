package com.java.billingservice.mapper.item;

import com.java.billingservice.dto.item.BillItemResponseDTO;
import com.java.billingservice.model.BillItem;

public class BillItemMapper {

    public static BillItemResponseDTO toBillItemResponseDTO(BillItem billItem) {
        BillItemResponseDTO billItemResponseDTO = new BillItemResponseDTO();
        billItemResponseDTO.setId(billItem.getId().toString());
        billItemResponseDTO.setTreatmentId(billItem.getTreatmentId());
        billItemResponseDTO.setName(billItem.getName());
        billItemResponseDTO.setDescription(billItem.getDescription());
        billItemResponseDTO.setPrice(billItem.getPrice().toString());
        billItemResponseDTO.setBillId(billItem.getBill().getId().toString());

        return billItemResponseDTO;
    }

    public static BillItem toBillItem(BillItemResponseDTO billItemResponseDTO) {
        BillItem billItem = new BillItem();
        billItem.setTreatmentId(billItemResponseDTO.getTreatmentId());
        billItem.setName(billItemResponseDTO.getName());
        billItem.setDescription(billItemResponseDTO.getDescription());
        billItem.setPrice(billItem.getPrice());

        return billItem;
    }
}
