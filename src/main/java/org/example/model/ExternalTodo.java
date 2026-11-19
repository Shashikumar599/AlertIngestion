package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Shape of https://jsonplaceholder.typicode.com/todos/{id}
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalTodo {
    private int userId;
    private int id;
    private String title;
    private boolean completed;
}
