package com.java.treatmentservice.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentRepository {

    private final TreatmentRepository treatmentRepository;

    public TreatmentRepository(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

//    public List<TreatmentReponseDTO> getTreatments() {
//
//    }
}
