package com.java.billingservice.mapper.bill;

import com.java.billingservice.dto.bill.BillRequestDTO;
import com.java.billingservice.dto.bill.BillResponseDTO;
import com.java.billingservice.mapper.item.BillItemMapper;
import com.java.billingservice.model.Bill;

import java.util.UUID;
import java.util.stream.Collectors;

public class BillMapper {

    public static BillResponseDTO toBillResponseDTO(Bill bill) {
        return BillResponseDTO.builder()
                .id(bill.getId().toString())
                .patientId(bill.getPatientId().toString())
                .amount(bill.getAmount().toString())
                .billingAccountId(bill.getBillingAccount().toString())
                .createdDate(bill.getCreatedDate().toString())
                .payedDate(bill.getPayedDate() != null ? bill.getPayedDate().toString() : null)
                .status(bill.getStatus())
                .billItems(bill.getBillItems().stream().map(BillItemMapper::toBillItemResponseDTO).collect(Collectors.toList()))
                .build();
    }

    public static Bill toBill(BillRequestDTO billRequestDTO) {
        return Bill.builder()
                .patientId(UUID.fromString(billRequestDTO.getPatientId()))
                .amount(Long.valueOf(billRequestDTO.getAmount()))
                .status(billRequestDTO.getStatus())
                .build();
    }
}
