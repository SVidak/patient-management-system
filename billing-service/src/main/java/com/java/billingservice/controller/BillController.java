package com.java.billingservice.controller;

import com.java.billingservice.dto.bill.BillRequestDTO;
import com.java.billingservice.dto.bill.BillResponseDTO;
import com.java.billingservice.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public ResponseEntity<List<BillResponseDTO>> getBills() {

        return ResponseEntity.ok(billService.getBills());
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<BillResponseDTO>> getBillsByPatientId(@PathVariable UUID patientId) {

        return ResponseEntity.ok(billService.getBillsByPatientId(patientId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponseDTO> getBillById(@PathVariable UUID id) {

        return ResponseEntity.ok(billService.getBillById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<BillResponseDTO> createBill(@RequestBody BillRequestDTO billRequestDTO) {

        return ResponseEntity.ok(billService.createBill(billRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillResponseDTO> updateBill(@PathVariable UUID id, @RequestBody BillRequestDTO billRequestDTO) {

        return ResponseEntity.ok(billService.updateBill(id, billRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable UUID id) {
        billService.deleteBillById(id);
        return ResponseEntity.noContent().build();
    }
}
