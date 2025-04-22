package com.java.billingservice.controller;

import com.java.billingservice.dto.bill.BillRequestDTO;
import com.java.billingservice.dto.bill.BillResponseDTO;
import com.java.billingservice.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/create/{treatmentIds}")
    public ResponseEntity<BillResponseDTO> createBill(@RequestBody BillRequestDTO billRequestDTO, @PathVariable List<String> treatmentIds) {

        return ResponseEntity.ok(billService.createBill(billRequestDTO, treatmentIds));
    }

}
