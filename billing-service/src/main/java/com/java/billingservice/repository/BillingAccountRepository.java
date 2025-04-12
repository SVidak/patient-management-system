package com.java.billingservice.repository;

import com.java.billingservice.model.BillingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillingAccountRepository extends JpaRepository<BillingAccount, UUID> {
    boolean existsByPatientId(UUID uuid);
}
