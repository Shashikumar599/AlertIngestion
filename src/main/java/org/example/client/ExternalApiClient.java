package org.example.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.ExternalServiceException;
import org.example.model.ExternalTodo;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExternalApiClient {

    private final RestClient externalRestClient;

    // Outbound GET example
    public ExternalTodo fetchTodo(int id) {
        log.info("Calling external API GET /todos/{}", id);
        try {
            ExternalTodo todo = externalRestClient.get()
                    .uri("/todos/{id}", id)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new ExternalServiceException("GET /todos/" + id + " returned " + res.getStatusCode(), null);
                    })
                    .body(ExternalTodo.class);
            log.info("External API response: {}", todo);
            return todo;
        } catch (RestClientException e) { // timeouts, connection refused, bad JSON
            throw new ExternalServiceException("GET /todos/" + id + " failed: " + e.getMessage(), e);
        }
    }

    // Outbound POST example
    public ExternalTodo createTodo(ExternalTodo request) {
        log.info("Calling external API POST /todos body={}", request);
        try {
            ExternalTodo created = externalRestClient.post()
                    .uri("/todos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new ExternalServiceException("POST /todos returned " + res.getStatusCode(), null);
                    })
                    .body(ExternalTodo.class);
            log.info("External API response: {}", created);
            return created;
        } catch (RestClientException e) {
            throw new ExternalServiceException("POST /todos failed: " + e.getMessage(), e);
        }
    }
}
