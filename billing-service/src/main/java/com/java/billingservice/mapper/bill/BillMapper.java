package com.java.billingservice.mapper.bill;

import com.java.billingservice.dto.bill.BillRequestDTO;
import com.java.billingservice.dto.bill.BillResponseDTO;
import com.java.billingservice.mapper.item.BillItemMapper;
import com.java.billingservice.model.Bill;

import java.util.UUID;
import java.util.stream.Collectors;

public class BillMapper {

    public static BillResponseDTO toBillResponseDTO(Bill bill) {
        BillResponseDTO billResponseDTO = new BillResponseDTO();
        billResponseDTO.setId(bill.getId().toString());
        billResponseDTO.setPatientId(bill.getPatientId().toString());
        billResponseDTO.setAmount(bill.getAmount().toString());
        billResponseDTO.setBillingAccountId(bill.getBillingAccount().getId().toString());
        billResponseDTO.setCreatedDate(bill.getCreatedDate().toString());
        billResponseDTO.setPayedDate(bill.getPayedDate() != null ?
                bill.getPayedDate().toString() : null);
        billResponseDTO.setStatus(bill.getStatus());
        billResponseDTO.setBillItems(bill.getBillItems().stream().map(BillItemMapper::toBillItemResponseDTO).collect(Collectors.toList()));

        return billResponseDTO;
    }

    public static Bill toBill(BillRequestDTO billRequestDTO) {
        Bill bill = new Bill();
        bill.setPatientId(UUID.fromString(billRequestDTO.getPatientId()));
        bill.setAmount(Long.valueOf(billRequestDTO.getAmount()));
        bill.setStatus(billRequestDTO.getStatus());

        return bill;
    }
}
