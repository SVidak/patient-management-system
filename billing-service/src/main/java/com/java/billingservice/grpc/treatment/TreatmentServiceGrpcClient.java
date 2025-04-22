package com.java.billingservice.grpc.treatment;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import treatment.*;

@Service
public class TreatmentServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(TreatmentServiceGrpcClient.class);
    private final TreatmentServiceGrpc.TreatmentServiceBlockingStub blockingStub;


    public TreatmentServiceGrpcClient(@Value("${treatment.service.address:localhost}") String serverAddress, @Value("${treatment.service.grpc.port:9500}") int serverPort) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();

        blockingStub = TreatmentServiceGrpc.newBlockingStub(channel);
    }

    public GetTreatmentByIdResponse getTreatmentById(String treatmentId) {

        GetTreatmentByIdRequest request = GetTreatmentByIdRequest.newBuilder().setId(treatmentId).build();

        GetTreatmentByIdResponse response = blockingStub.getTreatmentById(request);
        log.info("Received response from Treatment Service via GRPC: {}", response);

        return response;
    }

    public GetAllTreatmentsResponse getAllTreatments() {

        GetAllTreatmentsRequest request = GetAllTreatmentsRequest.newBuilder().build();

        GetAllTreatmentsResponse response = blockingStub.getAllTreatments(request);
        log.info("Received response from Treatment Service via GRPC: {}", response);

        return response;
    }

    public CreateTreatmentResponse createTreatment(Treatment treatment) {

        CreateTreatmentRequest request = CreateTreatmentRequest.newBuilder()
                .setName(treatment.getName())
                .setDescription(treatment.getDescription())
                .setPrice(treatment.getPrice())
                .setDuration(treatment.getDuration())
                .build();

        CreateTreatmentResponse response = blockingStub.createTreatment(request);
        log.info("Received response from Treatment Service via GRPC: {}", response);

        return response;
    }

    public UpdateTreatmentResponse updateTreatment(Treatment treatment) {

        UpdateTreatmentRequest request = UpdateTreatmentRequest.newBuilder()
                .setId(treatment.getId())
                .setName(treatment.getName())
                .setDescription(treatment.getDescription())
                .setPrice(treatment.getPrice())
                .setDuration(treatment.getDuration())
                .build();

        UpdateTreatmentResponse response = blockingStub.updateTreatment(request);
        log.info("Received response from Treatment Service via GRPC: {}", response);

        return response;
    }

    public DeleteTreatmentResponse deleteTreatment(Treatment treatment) {

        DeleteTreatmentRequest request = DeleteTreatmentRequest.newBuilder().setId(treatment.getId()).build();

        DeleteTreatmentResponse response = blockingStub.deleteTreatment(request);
        log.info("Received response from Treatment Service via GRPC: {}", response);

        return response;
    }
}
