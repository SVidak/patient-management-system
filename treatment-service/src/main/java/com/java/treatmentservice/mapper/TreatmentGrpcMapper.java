package com.java.treatmentservice.mapper;

import com.java.treatmentservice.dto.TreatmentResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TreatmentGrpcMapper {

    public static treatment.Treatment toGrpc(TreatmentResponseDTO treatmentResponseDTO) {

        return treatment.Treatment.newBuilder()
                .setId(treatmentResponseDTO.getId())
                .setName(treatmentResponseDTO.getName())
                .setDescription(treatmentResponseDTO.getDescription())
                .setPrice(treatmentResponseDTO.getPrice())
                .setDuration(treatmentResponseDTO.getDuration())
                .build();
    }

    public static List<treatment.Treatment> toListGrpc(List<TreatmentResponseDTO> treatmentResponseDTOList) {
        return treatmentResponseDTOList.stream().map(TreatmentGrpcMapper::toGrpc).collect(Collectors.toList());
    }
}
