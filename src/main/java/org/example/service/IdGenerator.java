package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class IdGenerator {
    private AtomicInteger currId = new AtomicInteger();

    public int getCurrId() {
        return currId.incrementAndGet();
    }
}
