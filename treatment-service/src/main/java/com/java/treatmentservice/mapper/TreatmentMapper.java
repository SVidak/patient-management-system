package com.java.treatmentservice.mapper;

import com.java.treatmentservice.dto.TreatmentRequestDTO;
import com.java.treatmentservice.dto.TreatmentResponseDTO;
import com.java.treatmentservice.model.Treatment;

public class TreatmentMapper {

    public static TreatmentResponseDTO toTreatmentResponseDTO(Treatment treatment) {
        return TreatmentResponseDTO.builder()
                .id(treatment.getId())
                .name(treatment.getName())
                .description(treatment.getDescription())
                .price(treatment.getPrice())
                .duration(treatment.getDuration())
                .build();
    }

    public static Treatment toTreatment(TreatmentRequestDTO treatmentRequestDTO) {
        return Treatment.builder()
                .name(treatmentRequestDTO.getName())
                .description(treatmentRequestDTO.getDescription())
                .price(treatmentRequestDTO.getPrice())
                .duration(treatmentRequestDTO.getDuration())
                .build();
    }
}
