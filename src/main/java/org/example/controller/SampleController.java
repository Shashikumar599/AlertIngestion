package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.SampleRequest;
import org.example.model.SampleResponse;
import org.example.service.SampleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/samples")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SampleResponse create(@Valid @RequestBody SampleRequest request) {
        log.info("POST /api/samples body={}", request);
        return sampleService.createSample(request);
    }

    @GetMapping("/{id}")
    public SampleResponse get(@PathVariable String id) {
        log.info("GET /api/samples/{}", id);
        return sampleService.getSample(id);
    }

    @GetMapping
    public List<SampleResponse> getAll() {
        log.info("GET /api/samples");
        return sampleService.getAllSamples();
    }
}
