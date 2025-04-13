package com.java.billingservice.grpc;

import billing.BillingServiceGrpc;
import com.java.billingservice.dto.BillingAccountRequestDTO;
import com.java.billingservice.dto.BillingAccountResponseDTO;
import com.java.billingservice.service.BillingAccountService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.UUID;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private final BillingAccountService billingAccountService;

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    public BillingGrpcService(BillingAccountService billingAccountService) {
        this.billingAccountService = billingAccountService;
    }

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<billing.BillingResponse> responseObserver) {

        log.info("createBillingAccount request received: {}", billingRequest.toString());

        BillingAccountRequestDTO billingAccountRequestDTO = BillingAccountRequestDTO.builder()
                .patientId(String.valueOf(UUID.fromString(billingRequest.getPatientId())))
                .build();

        BillingAccountResponseDTO billingAccountResponseDTO = billingAccountService.createBillingAccount(billingAccountRequestDTO);

        billing.BillingResponse response = billing.BillingResponse.newBuilder().setAccountId(billingAccountResponseDTO.getId()).setStatus("ACTIVE").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
