package com.java.treatmentservice.repository;

import com.java.treatmentservice.model.Treatment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends MongoRepository<Treatment, String> {
}
