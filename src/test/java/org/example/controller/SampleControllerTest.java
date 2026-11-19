package org.example.controller;

import org.example.client.ExternalApiClient;
import org.example.model.ExternalTodo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // no real outbound call in tests
    private ExternalApiClient externalApiClient;

    @Test
    void createSample_returns201() throws Exception {
        when(externalApiClient.fetchTodo(anyInt())).thenReturn(new ExternalTodo(1, 1, "mock", false));

        mockMvc.perform(post("/api/samples")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"customerId":"c-1","item":"laptop","quantity":2}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("CREATED"))
                .andExpect(jsonPath("$.external.title").value("mock"));
    }

    @Test
    void createSample_invalidBody_returns400() throws Exception {
        mockMvc.perform(post("/api/samples")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"customerId":"","item":"laptop","quantity":0}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.length()").value(2));
    }

    @Test
    void getSample_unknownId_returns404() throws Exception {
        mockMvc.perform(get("/api/samples/unknown"))
                .andExpect(status().isNotFound());
    }
}
