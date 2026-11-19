package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ExternalApiClient;
import org.example.exception.NotFoundException;
import org.example.model.ExternalTodo;
import org.example.model.Sample;
import org.example.model.SampleRequest;
import org.example.model.SampleResponse;
import org.example.repository.SampleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {

    private final ExternalApiClient externalApiClient;
    private final SampleRepository sampleRepository;

    public SampleResponse createSample(SampleRequest request) {
        log.info("Service received: {}", request);

        ExternalTodo external = externalApiClient.fetchTodo(1);

        Sample sample = sampleRepository.save(Sample.builder()
                .id(UUID.randomUUID().toString())
                .customerId(request.getCustomerId())
                .item(request.getItem())
                .quantity(request.getQuantity())
                .status("CREATED")
                .createdAt(Instant.now())
                .build());
        log.info("Saved sample: {}", sample);

        return toResponse(sample, external);
    }

    public SampleResponse getSample(String id) {
        return sampleRepository.findById(id)
                .map(sample -> toResponse(sample, null))
                .orElseThrow(() -> new NotFoundException("Sample not found: " + id));
    }

    public List<SampleResponse> getAllSamples() {
        return sampleRepository.findAll().stream()
                .map(sample -> toResponse(sample, null))
                .toList();
    }

    private SampleResponse toResponse(Sample sample, ExternalTodo external) {
        SampleRequest request = SampleRequest.builder()
                .customerId(sample.getCustomerId())
                .item(sample.getItem())
                .quantity(sample.getQuantity())
                .build();
        return SampleResponse.builder()
                .sampleId(sample.getId())
                .status(sample.getStatus())
                .request(request)
                .external(external)
                .createdAt(sample.getCreatedAt())
                .build();
    }
}
