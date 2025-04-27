package com.java.treatmentservice.service;

import com.java.treatmentservice.dto.TreatmentRequestDTO;
import com.java.treatmentservice.dto.TreatmentResponseDTO;
import com.java.treatmentservice.exception.TreatmentNotFoundException;
import com.java.treatmentservice.mapper.TreatmentMapper;
import com.java.treatmentservice.model.Treatment;
import com.java.treatmentservice.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<TreatmentResponseDTO> getTreatments() {
        List<Treatment> treatments = treatmentRepository.findAll();

        return treatments.stream().map(TreatmentMapper::toTreatmentResponseDTO).toList();
    }

    public TreatmentResponseDTO getTreatmentById(String id) {

        return TreatmentMapper.toTreatmentResponseDTO(treatmentRepository.findById(id)
                .orElseThrow(() -> new TreatmentNotFoundException("Treatment with ID: " + id + "not found")));
    }

    public TreatmentResponseDTO createTreatment(TreatmentRequestDTO treatmentRequestDTO) {

        Treatment newTreatment = TreatmentMapper.toTreatment(treatmentRequestDTO);

        return TreatmentMapper.toTreatmentResponseDTO(treatmentRepository.save(newTreatment));
    }

    public TreatmentResponseDTO updateTreatment(String id, TreatmentRequestDTO treatmentRequestDTO) {

        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new TreatmentNotFoundException("Treatment with ID: " + id + "not found"));

        treatment.setName(treatmentRequestDTO.getName());
        treatment.setDescription(treatmentRequestDTO.getDescription());
        treatment.setPrice(treatmentRequestDTO.getPrice());
        treatment.setDuration(treatmentRequestDTO.getDuration());
        treatmentRepository.save(treatment);

        return TreatmentMapper.toTreatmentResponseDTO(treatment);
    }

    public void deleteTreatment(String id) {
        treatmentRepository.deleteById(id);
    }
}
