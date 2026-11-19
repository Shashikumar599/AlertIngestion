package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the Spring Boot app.
 *
 * Flow: SampleController -> SampleService -> ExternalApiClient (RestClient) + SampleRepository (in-memory)
 * Errors: GlobalExceptionHandler maps exceptions to JSON (400 / 404 / 502 / 500)
 * Config: src/main/resources/application.yml (port, external URL, timeouts, log levels)
 *
 * Run (from project root):
 *   mvn spring-boot:run
 *   mvn test                      (run tests)
 *   or: mvn package -DskipTests && java -jar target/interview-1.0-SNAPSHOT.jar
 *
 * Test:
 *   curl -X POST localhost:8080/api/samples -H 'Content-Type: application/json' \
 *        -d '{"customerId":"c-123","item":"laptop","quantity":2}'
 *   or use requests.http in IntelliJ
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
