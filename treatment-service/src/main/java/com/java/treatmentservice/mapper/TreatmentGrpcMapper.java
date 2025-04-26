package com.java.treatmentservice.mapper;

import com.java.treatmentservice.dto.TreatmentRequestDTO;
import com.java.treatmentservice.dto.TreatmentResponseDTO;
import treatment.*;

import java.util.List;
import java.util.stream.Collectors;

public class TreatmentGrpcMapper {

    public static Treatment toGrpc(TreatmentResponseDTO treatmentResponseDTO) {
        return Treatment.newBuilder()
                .setId(treatmentResponseDTO.getId())
                .setName(treatmentResponseDTO.getName())
                .setDescription(treatmentResponseDTO.getDescription())
                .setPrice(treatmentResponseDTO.getPrice())
                .setDuration(treatmentResponseDTO.getDuration())
                .build();
    }

    public static List<Treatment> toListGrpc(List<TreatmentResponseDTO> treatmentResponseDTOList) {
        return treatmentResponseDTOList.stream().map(TreatmentGrpcMapper::toGrpc).collect(Collectors.toList());
    }

    public static TreatmentRequestDTO toTreatmentRequestDTO(CreateTreatmentRequest createTreatmentRequest) {
        return TreatmentRequestDTO.builder()
                .name(createTreatmentRequest.getName())
                .description(createTreatmentRequest.getDescription())
                .price(createTreatmentRequest.getPrice())
                .duration(createTreatmentRequest.getDuration())
                .build();
    }

    public static TreatmentRequestDTO toTreatmentRequestDTO(UpdateTreatmentRequest updateTreatmentRequest) {
        return TreatmentRequestDTO.builder()
                .name(updateTreatmentRequest.getName())
                .description(updateTreatmentRequest.getDescription())
                .price(updateTreatmentRequest.getPrice())
                .duration(updateTreatmentRequest.getDuration())
                .build();
    }
}
