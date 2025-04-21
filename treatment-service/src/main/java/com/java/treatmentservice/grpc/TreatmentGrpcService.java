package com.java.treatmentservice.grpc;

import com.java.treatmentservice.dto.TreatmentRequestDTO;
import com.java.treatmentservice.dto.TreatmentResponseDTO;
import com.java.treatmentservice.service.TreatmentService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import treatment.CreateTreatmentResponse;
import treatment.TreatmentServiceGrpc;

import java.util.List;
import java.util.stream.Collectors;

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
    public void getAllTreatments(treatment.GetAllTreatmentsRequest getAllTreatmentsRequest, StreamObserver<treatment.GetAllTreatmentsResponse> responseObserver) {

        List<TreatmentResponseDTO> allTreatments = treatmentService.getTreatments();

        List<treatment.Treatment> grpcTreatments = allTreatments.stream().map(dto -> treatment.Treatment.newBuilder()
                .setId(dto.getId())
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setPrice(dto.getPrice())
                .setDuration(dto.getDuration())
                .build()).collect(Collectors.toList());

        treatment.GetAllTreatmentsResponse getAllTreatmentsResponse = treatment.GetAllTreatmentsResponse.newBuilder()
                .addAllTreatments(grpcTreatments).build();

        responseObserver.onNext(getAllTreatmentsResponse);
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

    @Override
    public void updateTreatment(treatment.UpdateTreatmentRequest updateTreatmentRequest, StreamObserver<treatment.UpdateTreatmentResponse> responseObserver) {

        TreatmentRequestDTO treatmentRequestDTO = new TreatmentRequestDTO();
        treatmentRequestDTO.setName(updateTreatmentRequest.getName());
        treatmentRequestDTO.setDescription(updateTreatmentRequest.getDescription());
        treatmentRequestDTO.setPrice(updateTreatmentRequest.getPrice());
        treatmentRequestDTO.setDuration(updateTreatmentRequest.getDuration());

        TreatmentResponseDTO treatmentResponseDTO = treatmentService.updateTreatment(updateTreatmentRequest.getId(), treatmentRequestDTO);

        treatment.Treatment grpcTreatment = treatment.Treatment.newBuilder()
                .setId(treatmentResponseDTO.getId())
                .setName(treatmentResponseDTO.getName())
                .setDescription(treatmentResponseDTO.getDescription())
                .setPrice(treatmentResponseDTO.getPrice())
                .setDuration(treatmentResponseDTO.getDuration())
                .build();

        treatment.UpdateTreatmentResponse updateTreatmentResponse = treatment.UpdateTreatmentResponse.newBuilder()
                .setTreatment(grpcTreatment).build();

        responseObserver.onNext(updateTreatmentResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteTreatment(treatment.DeleteTreatmentRequest deleteTreatmentRequest, StreamObserver<treatment.DeleteTreatmentResponse> responseObserver) {

        treatmentService.deleteTreatment(deleteTreatmentRequest.getId());

        responseObserver.onNext(treatment.DeleteTreatmentResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

}
