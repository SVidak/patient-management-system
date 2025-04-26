package com.java.billingservice.service;

import com.java.billingservice.dto.billing.BillingAccountRequestDTO;
import com.java.billingservice.dto.billing.BillingAccountResponseDTO;
import com.java.billingservice.exception.BillingAccountAlreadyExistsException;
import com.java.billingservice.exception.BillingAccountNotFoundException;
import com.java.billingservice.mapper.billing.BillingAccountMapper;
import com.java.billingservice.model.BillingAccount;
import com.java.billingservice.repository.BillingAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public BillingAccountResponseDTO getBillingAccountById(UUID id) {
        BillingAccount billingAccount = billingAccountRepository.findById(id)
                .orElseThrow(() -> new BillingAccountNotFoundException("BillingAccount with ID: " + id + "not found"));

        return BillingAccountMapper.toBillingAccountResponseDTO(billingAccount);
    }

    public BillingAccountResponseDTO getBillingAccountByPatientId(UUID patientId) {
        BillingAccount billingAccount = billingAccountRepository.findByPatientId(patientId)
                .orElseThrow(() -> new BillingAccountNotFoundException("BillingAccount with Patient ID: " + patientId + "not found"));

        return BillingAccountMapper.toBillingAccountResponseDTO(billingAccount);
    }

    public BillingAccountResponseDTO createBillingAccount(BillingAccountRequestDTO billingAccountRequestDTO) {

        if(billingAccountRepository.existsByPatientId(UUID.fromString(billingAccountRequestDTO.getPatientId())))
            throw new BillingAccountAlreadyExistsException("BillingAccount for Patient: " + billingAccountRequestDTO.getPatientId() + "already exists");

        BillingAccount billingAccount = BillingAccountMapper.toBillingAccount(billingAccountRequestDTO);
        billingAccount.setCreationDate(LocalDate.now());
        billingAccount.setLastUpdateDate(LocalDate.now());
        billingAccountRepository.save(billingAccount);

        return BillingAccountMapper.toBillingAccountResponseDTO(billingAccount);
    }

    public BillingAccountResponseDTO updateBillingAccount(UUID patientId, BillingAccountRequestDTO billingAccountRequestDTO) {

        BillingAccount billingAccount = billingAccountRepository.findByPatientId(patientId)
                .orElseThrow(() -> new BillingAccountNotFoundException("BillingAccount with Patient ID: " + patientId + "not found"));

        billingAccount.setLastUpdateDate(LocalDate.now());

        billingAccountRepository.save(billingAccount);

        return BillingAccountMapper.toBillingAccountResponseDTO(billingAccount);
    }

    public void deleteBillingAccountById(UUID id) {
        billingAccountRepository.deleteById(id);
    }

    public void deleteBillingAccountByPatientId(UUID patientId) {
        billingAccountRepository.deleteByPatientId(patientId);
    }
}
