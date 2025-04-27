package com.java.billingservice.service;

import com.java.billingservice.grpc.treatment.TreatmentServiceGrpcClient;
import com.java.billingservice.model.BillItem;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillItemService {

    private final TreatmentServiceGrpcClient treatmentServiceGrpcClient;

    public BillItemService(TreatmentServiceGrpcClient treatmentServiceGrpcClient) {
        this.treatmentServiceGrpcClient = treatmentServiceGrpcClient;
    }

    public List<BillItem> createBillItemsFromTreatmentIds(List<String> treatmentIds) {
        return treatmentIds.stream()
                .map(treatmentServiceGrpcClient::getTreatmentById)
                .map(response -> BillItem.builder()
                        .treatmentId(response.getTreatment().getId())
                        .name(response.getTreatment().getName())
                        .description(response.getTreatment().getDescription())
                        .price(Long.valueOf(response.getTreatment().getPrice()))
                        .build())
                .collect(Collectors.toList());
    }
}
