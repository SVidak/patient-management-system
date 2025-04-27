package com.java.billingservice.grpc.billing;

import billing.*;
import com.java.billingservice.dto.billing.BillingAccountRequestDTO;
import com.java.billingservice.dto.billing.BillingAccountResponseDTO;
import com.java.billingservice.service.BillingAccountService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private final BillingAccountService billingAccountService;

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    public BillingGrpcService(BillingAccountService billingAccountService) {
        this.billingAccountService = billingAccountService;
    }

    @Override
    public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {

        log.info("createBillingAccount request received: {}", billingRequest.toString());

        try {
            BillingAccountRequestDTO billingAccountRequestDTO = BillingAccountRequestDTO.builder()
                    .patientId(String.valueOf(UUID.fromString(billingRequest.getPatientId())))
                    .build();

            BillingAccountResponseDTO billingAccountResponseDTO = billingAccountService.createBillingAccount(billingAccountRequestDTO);

            BillingResponse response = BillingResponse.newBuilder().setAccountId(billingAccountResponseDTO.getId()).setStatus("ACTIVE").build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal server error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
}
