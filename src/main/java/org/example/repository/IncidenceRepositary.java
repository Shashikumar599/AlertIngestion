package org.example.repository;

import org.example.model.Incidence;
import org.example.model.Sample;

import java.util.List;
import java.util.Optional;

public interface IncidenceRepositary {
    Incidence save(Incidence incidence, String dedupKey);
    Optional<Incidence> findById(String id);
    List<Incidence> findAll();
}
