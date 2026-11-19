package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

// Entity-like class: would map to a DB table later
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sample {
    private String id;
    private String customerId;
    private String item;
    private int quantity;
    private String status;
    private Instant createdAt;
}
