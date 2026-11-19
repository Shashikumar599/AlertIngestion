package org.example.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Incidence;
import org.example.model.Sample;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Slf4j
public class IncidenceInMemoryRepository implements IncidenceRepositary{
    private final Map<String, Incidence> store = new ConcurrentHashMap<>();
//    private final HashSet<String> dedupSet = new HashSet<>();
    private final Map<String, Incidence> dedupSet = new ConcurrentHashMap<>();

    @Override
    public Incidence save(Incidence incidence,  String dedupKey) {
        if(dedupSet.containsKey(dedupKey)){
            log.warn("Duplicate Alert with dedup key {}", dedupKey);
            return dedupSet.get(dedupKey);
        }
        store.put(incidence.getId(), incidence);
        return incidence;
    }

    @Override
    public Optional<Incidence> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Incidence> findAll() {
        return new ArrayList<>(store.values());
    }
}
