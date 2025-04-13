package com.java.treatmentservice.controller;

import com.java.treatmentservice.dto.TreatmentRequestDTO;
import com.java.treatmentservice.dto.TreatmentResponseDTO;
import com.java.treatmentservice.service.TreatmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping
    public ResponseEntity<List<TreatmentResponseDTO>> getTreatments() {
        return ResponseEntity.ok(treatmentService.getTreatments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentResponseDTO> getTreatmentById(@PathVariable String id) {
        return ResponseEntity.ok(treatmentService.getTreatmentById(id));
    }

    @PostMapping
    public ResponseEntity<TreatmentResponseDTO> createTreatment(@RequestBody TreatmentRequestDTO treatmentRequestDTO) {
        return ResponseEntity.ok(treatmentService.createTreatment(treatmentRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreatmentResponseDTO> updateTreatment(@PathVariable String id, @RequestBody TreatmentRequestDTO treatmentRequestDTO) {
        return ResponseEntity.ok(treatmentService.updateTreatment(id, treatmentRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable String id) {
        treatmentService.deleteTreatment(id);
        return ResponseEntity.noContent().build();
    }
}
