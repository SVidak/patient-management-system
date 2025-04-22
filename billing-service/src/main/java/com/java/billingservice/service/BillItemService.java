package com.java.billingservice.service;

import com.java.billingservice.grpc.treatment.TreatmentServiceGrpcClient;
import com.java.billingservice.model.BillItem;
import com.java.billingservice.repository.BillItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BillItemService {

    private final BillItemRepository billItemRepository;
    private final TreatmentServiceGrpcClient treatmentServiceGrpcClient;

    public BillItemService(BillItemRepository billItemRepository, TreatmentServiceGrpcClient treatmentServiceGrpcClient) {
        this.billItemRepository = billItemRepository;
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
                .toList();
    }
}
