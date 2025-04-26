package com.java.billingservice.mapper.billing;

import com.java.billingservice.dto.billing.BillingAccountRequestDTO;
import com.java.billingservice.dto.billing.BillingAccountResponseDTO;
import com.java.billingservice.model.BillingAccount;

import java.util.UUID;

public class BillingAccountMapper {

    public static BillingAccountResponseDTO toBillingAccountResponseDTO(BillingAccount billingAccount) {
        return BillingAccountResponseDTO.builder()
                .id(billingAccount.getId().toString())
                .patientId(billingAccount.getPatientId().toString())
                .creationDate(billingAccount.getCreationDate().toString())
                .updateDate(billingAccount.getLastUpdateDate().toString())
                .build();
    }

    public static BillingAccount toBillingAccount(BillingAccountRequestDTO billingAccountRequestDTO) {
        return BillingAccount.builder()
                .patientId(UUID.fromString(billingAccountRequestDTO.getPatientId()))
                .build();
    }
}
