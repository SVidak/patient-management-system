package com.java.treatmentservice.controller;

import com.java.treatmentservice.dto.TreatmentRequestDTO;
import com.java.treatmentservice.dto.TreatmentResponseDTO;
import com.java.treatmentservice.service.TreatmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("treatments")
@Tag(name = "Treatment", description = "API for displaying Treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping
    @Operation(summary = "Get Treatments")
    public ResponseEntity<List<TreatmentResponseDTO>> getTreatments() {
        return ResponseEntity.ok(treatmentService.getTreatments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Treatment by ID")
    public ResponseEntity<TreatmentResponseDTO> getTreatmentById(@PathVariable String id) {
        return ResponseEntity.ok(treatmentService.getTreatmentById(id));
    }

    @PostMapping
    @Operation(summary = "Create Treatment")
    public ResponseEntity<TreatmentResponseDTO> createTreatment(@Valid @RequestBody TreatmentRequestDTO treatmentRequestDTO) {
        return ResponseEntity.ok(treatmentService.createTreatment(treatmentRequestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Treatment")
    public ResponseEntity<TreatmentResponseDTO> updateTreatment(@PathVariable String id, @Valid @RequestBody TreatmentRequestDTO treatmentRequestDTO) {
        return ResponseEntity.ok(treatmentService.updateTreatment(id, treatmentRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Treatment")
    public ResponseEntity<Void> deleteTreatment(@PathVariable String id) {
        treatmentService.deleteTreatment(id);
        return ResponseEntity.noContent().build();
    }
}
