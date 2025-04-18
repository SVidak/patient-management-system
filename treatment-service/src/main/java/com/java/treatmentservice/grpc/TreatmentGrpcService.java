package com.java.treatmentservice.grpc;

import com.java.treatmentservice.dto.TreatmentResponseDTO;
import com.java.treatmentservice.service.TreatmentService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
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


}
