package com.java.billingservice.controller;

import com.java.billingservice.dto.BillingAccountRequestDTO;
import com.java.billingservice.dto.BillingAccountResponseDTO;
import com.java.billingservice.service.BillingAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("billing-accounts")
public class BillingAccountController {

    private final BillingAccountService billingAccountService;

    public BillingAccountController(BillingAccountService billingAccountService) {
        this.billingAccountService = billingAccountService;
    }

    @GetMapping
    public ResponseEntity<List<BillingAccountResponseDTO>> getBillingAccounts() {
        return ResponseEntity.ok(billingAccountService.getBillingAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingAccountResponseDTO> getBillingAccountById(@PathVariable UUID id) {
        return ResponseEntity.ok(billingAccountService.getBillingAccountById(id));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<BillingAccountResponseDTO> getBillingAccountByPatientId(@PathVariable UUID patientId) {
        return ResponseEntity.ok(billingAccountService.getBillingAccountByPatientId(patientId));
    }

    @PostMapping
    public ResponseEntity<BillingAccountResponseDTO> createBillingAccount(@Validated @RequestBody BillingAccountRequestDTO billingAccountRequestDTO) {
        return ResponseEntity.ok(billingAccountService.createBillingAccount(billingAccountRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBillingAccountById(@PathVariable UUID id) {
        billingAccountService.deleteBillingAccountById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deleteBillingAccountByPatientId(@PathVariable UUID patientId) {
        billingAccountService.deleteBillingAccountByPatientId(patientId);
        return ResponseEntity.noContent().build();
    }

}
