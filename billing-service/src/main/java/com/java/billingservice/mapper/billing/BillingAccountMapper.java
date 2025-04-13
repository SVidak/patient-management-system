package com.java.billingservice.mapper.billing;

import com.java.billingservice.dto.billing.BillingAccountRequestDTO;
import com.java.billingservice.dto.billing.BillingAccountResponseDTO;
import com.java.billingservice.model.BillingAccount;

import java.util.UUID;

public class BillingAccountMapper {

    public static BillingAccountResponseDTO toBillingAccountResponseDTO(BillingAccount billingAccount) {
        BillingAccountResponseDTO billingAccountResponseDTO = new BillingAccountResponseDTO();
        billingAccountResponseDTO.setId(billingAccount.getId().toString());
        billingAccountResponseDTO.setPatientId(billingAccount.getPatientId().toString());
        billingAccountResponseDTO.setCreationDate(billingAccount.getCreationDate().toString());
        billingAccountResponseDTO.setUpdateDate(billingAccount.getLastUpdateDate().toString());

        return billingAccountResponseDTO;
    }

    public static BillingAccount toBillingAccount(BillingAccountRequestDTO billingAccountRequestDTO) {
        BillingAccount billingAccount = new BillingAccount();
        billingAccount.setPatientId(UUID.fromString(billingAccountRequestDTO.getPatientId()));

        return billingAccount;
    }
}
