package com.java.treatmentservice.grpc;

import com.java.treatmentservice.dto.TreatmentRequestDTO;
import com.java.treatmentservice.dto.TreatmentResponseDTO;
import com.java.treatmentservice.exception.TreatmentNotFoundException;
import com.java.treatmentservice.mapper.TreatmentGrpcMapper;
import com.java.treatmentservice.service.TreatmentService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import treatment.*;
import java.util.List;

@GrpcService
public class TreatmentGrpcService extends TreatmentServiceGrpc.TreatmentServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(TreatmentGrpcService.class);
    private final TreatmentService treatmentService;

    public TreatmentGrpcService(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @Override
    public void getTreatmentById(GetTreatmentByIdRequest getTreatmentByIdRequest, StreamObserver<GetTreatmentByIdResponse> responseObserver) {

        log.info("getTreatmentById request received: {}", getTreatmentByIdRequest.toString());

        try {
            TreatmentResponseDTO treatmentResponseDTO = treatmentService.getTreatmentById(getTreatmentByIdRequest.getId());

            Treatment grpcTreatment = TreatmentGrpcMapper.toGrpc(treatmentResponseDTO);

            GetTreatmentByIdResponse getTreatmentByIdResponse = GetTreatmentByIdResponse.newBuilder()
                    .setTreatment(grpcTreatment).build();


            responseObserver.onNext(getTreatmentByIdResponse);
            responseObserver.onCompleted();
        } catch (TreatmentNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Treatment not found with ID: " + getTreatmentByIdRequest.getId())
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal server error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getAllTreatments(GetAllTreatmentsRequest getAllTreatmentsRequest, StreamObserver<GetAllTreatmentsResponse> responseObserver) {

        log.info("getAllTreatments request received: {}", getAllTreatmentsRequest.toString());

        try {
            List<TreatmentResponseDTO> allTreatments = treatmentService.getTreatments();

            List<Treatment> grpcTreatments = TreatmentGrpcMapper.toListGrpc(allTreatments);

            GetAllTreatmentsResponse getAllTreatmentsResponse = GetAllTreatmentsResponse.newBuilder()
                    .addAllTreatments(grpcTreatments).build();

            responseObserver.onNext(getAllTreatmentsResponse);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal server error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void createTreatment(CreateTreatmentRequest createTreatmentRequest, StreamObserver<CreateTreatmentResponse> responseObserver) {

        log.info("createTreatmentRequest request received: {}", createTreatmentRequest.toString());

        try {
            TreatmentRequestDTO treatmentRequestDTO = TreatmentGrpcMapper.toTreatmentRequestDTO(createTreatmentRequest);

            TreatmentResponseDTO treatmentResponseDTO = treatmentService.createTreatment(treatmentRequestDTO);

            Treatment grpcTreatment = TreatmentGrpcMapper.toGrpc(treatmentResponseDTO);

            CreateTreatmentResponse createTreatmentResponse = CreateTreatmentResponse.newBuilder()
                    .setTreatment(grpcTreatment).build();

            responseObserver.onNext(createTreatmentResponse);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal server error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void updateTreatment(UpdateTreatmentRequest updateTreatmentRequest, StreamObserver<UpdateTreatmentResponse> responseObserver) {

        log.info("updateTreatmentRequest request received: {}", updateTreatmentRequest.toString());

        try {
            TreatmentRequestDTO treatmentRequestDTO = TreatmentGrpcMapper.toTreatmentRequestDTO(updateTreatmentRequest);

            TreatmentResponseDTO treatmentResponseDTO = treatmentService.updateTreatment(updateTreatmentRequest.getId(), treatmentRequestDTO);

            Treatment grpcTreatment = TreatmentGrpcMapper.toGrpc(treatmentResponseDTO);

            UpdateTreatmentResponse updateTreatmentResponse = UpdateTreatmentResponse.newBuilder()
                    .setTreatment(grpcTreatment).build();

            responseObserver.onNext(updateTreatmentResponse);
            responseObserver.onCompleted();
        } catch (TreatmentNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Treatment not found with ID: " + updateTreatmentRequest.getId())
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal server error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void deleteTreatment(DeleteTreatmentRequest deleteTreatmentRequest, StreamObserver<DeleteTreatmentResponse> responseObserver) {

        log.info("deleteTreatmentRequest request received: {}", deleteTreatmentRequest.toString());

        try {
            treatmentService.deleteTreatment(deleteTreatmentRequest.getId());

            responseObserver.onNext(DeleteTreatmentResponse.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal server error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
}
