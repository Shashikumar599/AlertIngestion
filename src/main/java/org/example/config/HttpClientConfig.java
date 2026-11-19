package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class HttpClientConfig {

    @Bean
    public RestClient externalRestClient(RestClient.Builder builder,
                                         @Value("${external.base-url}") String baseUrl,
                                         @Value("${external.connect-timeout:2s}") Duration connectTimeout,
                                         @Value("${external.read-timeout:5s}") Duration readTimeout) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        return builder
                .baseUrl(baseUrl)
                .requestFactory(factory)
                .build();
    }
}
