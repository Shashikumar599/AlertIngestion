package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ExternalApiClient;
import org.example.exception.NotFoundException;
import org.example.model.*;
import org.example.repository.IncidenceRepositary;
import org.example.repository.SampleRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlertService {

    private final ExternalApiClient externalApiClient;
    private final SampleRepository sampleRepository;
    private final IdGenerator idGenerator;
    private final IncidenceRepositary incidenceRepositary;

    private final SecureRandom secureRandom = new SecureRandom();

    public Incidence createSample(Alert alert) {
        log.debug("Alert received: {}", alert);

        // store incidence
        String id = String.valueOf(idGenerator.getCurrId());

        Incidence incidence =  new Incidence(id, alert.getSeverity(),
                alert.getServiceCode(),NotificationStatus.PENDING, 0);
        incidenceRepositary.save(incidence, alert.getDedupKey());



        //push to notification Channel

        return incidence;

//        return toResponse(sample, external);
    }
}
