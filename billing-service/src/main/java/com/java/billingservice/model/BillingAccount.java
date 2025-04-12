package com.java.billingservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private UUID patientId;

    @NotNull
    private LocalDate creationDate;

    @NotNull
    private LocalDate lastUpdateDate;

    @OneToMany(mappedBy = "billingAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bill> bills;
}
