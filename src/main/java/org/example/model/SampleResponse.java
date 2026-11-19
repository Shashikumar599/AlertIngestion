package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleResponse {
    private String sampleId;
    private String status;
    private SampleRequest request;
    private ExternalTodo external;
    private Instant createdAt;
}
