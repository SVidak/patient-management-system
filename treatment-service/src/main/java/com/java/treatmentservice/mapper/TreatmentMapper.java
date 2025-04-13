package com.java.treatmentservice.mapper;

import com.java.treatmentservice.dto.TreatmentRequestDTO;
import com.java.treatmentservice.dto.TreatmentResponseDTO;
import com.java.treatmentservice.model.Treatment;

public class TreatmentMapper {

    public static TreatmentResponseDTO toTreatmentResponseDTO(Treatment treatment) {
        TreatmentResponseDTO treatmentResponseDTO = new TreatmentResponseDTO();
        treatmentResponseDTO.setId(treatment.getId());
        treatmentResponseDTO.setName(treatment.getName());
        treatmentResponseDTO.setDescription(treatment.getDescription());
        treatmentResponseDTO.setPrice(treatment.getPrice());
        treatmentResponseDTO.setDuration(treatment.getDuration());

        return treatmentResponseDTO;
    }

    public static Treatment toTreatment(TreatmentRequestDTO treatmentRequestDTO) {
        Treatment treatment = new Treatment();
        treatment.setName(treatmentRequestDTO.getName());
        treatment.setDescription(treatmentRequestDTO.getDescription());
        treatment.setPrice(treatmentRequestDTO.getPrice());
        treatment.setDuration(treatmentRequestDTO.getDuration());

        return treatment;
    }
}
