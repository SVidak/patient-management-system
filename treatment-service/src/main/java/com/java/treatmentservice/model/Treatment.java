package com.java.treatmentservice.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Treatment {

    @Id
    private String id;

    @NotNull
    private String name;

    private String description;
    private String price;
    private String duration;
}
