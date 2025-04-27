package com.java.billingservice.controller;

import com.java.billingservice.dto.billing.BillingAccountRequestDTO;
import com.java.billingservice.dto.billing.BillingAccountResponseDTO;
import com.java.billingservice.service.BillingAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("billing-accounts")
@Tag(name = "Billing-Accounts", description = "API for managing Billing-Accounts")
public class BillingAccountController {

    private final BillingAccountService billingAccountService;

    public BillingAccountController(BillingAccountService billingAccountService) {
        this.billingAccountService = billingAccountService;
    }

    @GetMapping
    @Operation(summary = "Get Billing-Accounts")
    public ResponseEntity<List<BillingAccountResponseDTO>> getBillingAccounts() {
        return ResponseEntity.ok(billingAccountService.getBillingAccounts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Billing-Account by ID")
    public ResponseEntity<BillingAccountResponseDTO> getBillingAccountById(@PathVariable UUID id) {
        return ResponseEntity.ok(billingAccountService.getBillingAccountById(id));
    }

    @GetMapping("/{patientId}")
    @Operation(summary = "Get Billing-Account by Patient ID")
    public ResponseEntity<BillingAccountResponseDTO> getBillingAccountByPatientId(@PathVariable UUID patientId) {
        return ResponseEntity.ok(billingAccountService.getBillingAccountByPatientId(patientId));
    }

    @PostMapping
    @Operation(summary = "Create Billing-Account")
    public ResponseEntity<BillingAccountResponseDTO> createBillingAccount(@Validated @RequestBody BillingAccountRequestDTO billingAccountRequestDTO) {
        return ResponseEntity.ok(billingAccountService.createBillingAccount(billingAccountRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Billing-Account by ID")
    public ResponseEntity<Void> deleteBillingAccountById(@PathVariable UUID id) {
        billingAccountService.deleteBillingAccountById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{patientId}")
    @Operation(summary = "Delete Billing-Account by Patient ID")
    public ResponseEntity<Void> deleteBillingAccountByPatientId(@PathVariable UUID patientId) {
        billingAccountService.deleteBillingAccountByPatientId(patientId);
        return ResponseEntity.noContent().build();
    }

}
