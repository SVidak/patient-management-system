package com.java.treatmentservice.grpc;

import com.java.treatmentservice.dto.TreatmentRequestDTO;
import com.java.treatmentservice.dto.TreatmentResponseDTO;
import com.java.treatmentservice.service.TreatmentService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import treatment.CreateTreatmentResponse;
import treatment.TreatmentServiceGrpc;

@GrpcService
public class TreatmentGrpcService extends TreatmentServiceGrpc.TreatmentServiceImplBase {

    private final TreatmentService treatmentService;

    public TreatmentGrpcService(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @Override
    public void getTreatmentById(treatment.GetTreatmentByIdRequest getTreatmentByIdRequest, StreamObserver<treatment.GetTreatmentByIdResponse> responseObserver) {

        TreatmentResponseDTO treatmentResponseDTO = treatmentService.getTreatmentById(getTreatmentByIdRequest.getId());

        treatment.Treatment grpcTreatment = treatment.Treatment.newBuilder()
                .setId(treatmentResponseDTO.getId())
                .setName(treatmentResponseDTO.getName())
                .setDescription(treatmentResponseDTO.getDescription())
                .setPrice(treatmentResponseDTO.getPrice())
                .setDuration(treatmentResponseDTO.getDuration())
                .build();

        treatment.GetTreatmentByIdResponse getTreatmentByIdResponse = treatment.GetTreatmentByIdResponse.newBuilder()
                .setTreatment(grpcTreatment).build();


        responseObserver.onNext(getTreatmentByIdResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void createTreatment(treatment.CreateTreatmentRequest createTreatmentRequest, StreamObserver<CreateTreatmentResponse> responseObserver) {

        TreatmentRequestDTO treatmentRequestDTO = new TreatmentRequestDTO();
        treatmentRequestDTO.setName(createTreatmentRequest.getName());
        treatmentRequestDTO.setDescription(createTreatmentRequest.getDescription());
        treatmentRequestDTO.setPrice(createTreatmentRequest.getPrice());
        treatmentRequestDTO.setDuration(createTreatmentRequest.getDuration());

        TreatmentResponseDTO treatmentResponseDTO = treatmentService.createTreatment(treatmentRequestDTO);

        treatment.Treatment grpcTreatment = treatment.Treatment.newBuilder()
                .setId(treatmentResponseDTO.getId())
                .setName(treatmentResponseDTO.getName())
                .setDescription(treatmentResponseDTO.getDescription())
                .setPrice(treatmentResponseDTO.getPrice())
                .setDuration(treatmentResponseDTO.getDuration())
                .build();

        treatment.CreateTreatmentResponse createTreatmentResponse = treatment.CreateTreatmentResponse.newBuilder()
                .setTreatment(grpcTreatment).build();

        responseObserver.onNext(createTreatmentResponse);
        responseObserver.onCompleted();

    }
}
