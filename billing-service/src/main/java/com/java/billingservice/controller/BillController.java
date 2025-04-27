package com.java.billingservice.controller;

import com.java.billingservice.dto.bill.BillRequestDTO;
import com.java.billingservice.dto.bill.BillResponseDTO;
import com.java.billingservice.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bills")
@Tag(name = "Bills", description = "API for managing Bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    @Operation(summary = "Get Bills")
    public ResponseEntity<List<BillResponseDTO>> getBills() {

        return ResponseEntity.ok(billService.getBills());
    }

    @GetMapping("/{patientId}")
    @Operation(summary = "Get Bills by Patient ID")
    public ResponseEntity<List<BillResponseDTO>> getBillsByPatientId(@PathVariable UUID patientId) {

        return ResponseEntity.ok(billService.getBillsByPatientId(patientId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Bill by Bill ID")
    public ResponseEntity<BillResponseDTO> getBillById(@PathVariable UUID id) {

        return ResponseEntity.ok(billService.getBillById(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Create Bill")
    public ResponseEntity<BillResponseDTO> createBill(@RequestBody BillRequestDTO billRequestDTO) {

        return ResponseEntity.ok(billService.createBill(billRequestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Bill")
    public ResponseEntity<BillResponseDTO> updateBill(@PathVariable UUID id, @RequestBody BillRequestDTO billRequestDTO) {

        return ResponseEntity.ok(billService.updateBill(id, billRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Bill")
    public ResponseEntity<Void> deleteBill(@PathVariable UUID id) {
        billService.deleteBillById(id);
        return ResponseEntity.noContent().build();
    }
}
