package com.java.patientservice.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private final billing.BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    public BillingServiceGrpcClient(@Value("${billing.service.address:localhost}") String serverAddress, @Value("${billing.service.grpc.port:9200}") int serverPort) {
        log.info("Connecting to Billing Service GRPC service at: {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();

        blockingStub = billing.BillingServiceGrpc.newBlockingStub(channel);
    }

    public billing.BillingResponse createBillingAccount(String patientId, String name, String email) {

        billing.BillingRequest request = billing.BillingRequest.newBuilder().setPatientId(patientId).setName(name).setEmail(email).build();

        billing.BillingResponse response = blockingStub.createBillingAccount(request);
        log.info("Received response from Billing Service via GRPC: {}", response);

        return response;
    }
}
