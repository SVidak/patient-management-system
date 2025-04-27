package com.java.billingservice.mapper.billing;

import com.java.billingservice.dto.billing.BillingAccountRequestDTO;
import com.java.billingservice.dto.billing.BillingAccountResponseDTO;
import com.java.billingservice.mapper.bill.BillMapper;
import com.java.billingservice.model.BillingAccount;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BillingAccountMapper {

    public static BillingAccountResponseDTO toBillingAccountResponseDTO(BillingAccount billingAccount) {
        return BillingAccountResponseDTO.builder()
                .id(billingAccount.getId().toString())
                .patientId(billingAccount.getPatientId().toString())
                .creationDate(billingAccount.getCreationDate().toString())
                .updateDate(billingAccount.getLastUpdateDate().toString())
                .bills(Optional.ofNullable(billingAccount.getBills())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(BillMapper::toBillResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static BillingAccount toBillingAccount(BillingAccountRequestDTO billingAccountRequestDTO) {
        return BillingAccount.builder()
                .patientId(UUID.fromString(billingAccountRequestDTO.getPatientId()))
                .build();
    }
}
