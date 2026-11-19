package org.example.repository;

import org.example.model.Sample;

import java.util.List;
import java.util.Optional;

// Swap the in-memory impl for a JPA/JDBC one later without touching the service
public interface SampleRepository {
    Sample save(Sample sample);
    Optional<Sample> findById(String id);
    List<Sample> findAll();
}
