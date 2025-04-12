package com.java.billingservice.service;

import com.java.billingservice.dto.BillingAccountRequestDTO;
import com.java.billingservice.dto.BillingAccountResponseDTO;
import com.java.billingservice.exception.BillingAccountAlreadyExistsException;
import com.java.billingservice.mapper.BillingAccountMapper;
import com.java.billingservice.model.BillingAccount;
import com.java.billingservice.repository.BillingAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BillingAccountService {

    private final BillingAccountRepository billingAccountRepository;

    public BillingAccountService(BillingAccountRepository billingAccountRepository) {
        this.billingAccountRepository = billingAccountRepository;
    }

    public List<BillingAccountResponseDTO> getBillingAccounts() {
        List<BillingAccount> billingAccounts = billingAccountRepository.findAll();

        return billingAccounts.stream().map(BillingAccountMapper::toBillingAccountResponseDTO).toList();
    }

    public BillingAccountResponseDTO createBillingAccount(BillingAccountRequestDTO billingAccountRequestDTO) {

        if(billingAccountRepository.existsByPatientId(UUID.fromString(billingAccountRequestDTO.getPatientId())))
            throw new BillingAccountAlreadyExistsException("BillingAccount for Patient: " + billingAccountRequestDTO.getPatientId() + "already exists");

        BillingAccount billingAccount = billingAccountRepository.save(BillingAccountMapper.toBillingAccount(billingAccountRequestDTO));

        return BillingAccountMapper.toBillingAccountResponseDTO(billingAccount);
    }
}
