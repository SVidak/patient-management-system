package com.java.billingservice.controller;

import com.java.billingservice.dto.BillingAccountResponseDTO;
import com.java.billingservice.service.BillingAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
