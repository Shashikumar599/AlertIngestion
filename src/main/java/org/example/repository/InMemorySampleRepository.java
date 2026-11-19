package org.example.repository;

import org.example.model.Sample;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemorySampleRepository implements SampleRepository {

    private final Map<String, Sample> store = new ConcurrentHashMap<>();

    @Override
    public Sample save(Sample sample) {
        store.put(sample.getId(), sample);
        return sample;
    }

    @Override
    public Optional<Sample> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Sample> findAll() {
        return new ArrayList<>(store.values());
    }
}
