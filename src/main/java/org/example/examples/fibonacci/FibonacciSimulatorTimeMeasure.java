package org.example.examples.fibonacci;

import java.time.Duration;
import java.time.Instant;
public class FibonacciSimulatorTimeMeasure implements FibonacciSimulator {
    private final FibonacciSimulator decoratedSimulator;
    
    private long lastTimeMillis;

    public FibonacciSimulatorTimeMeasure(FibonacciSimulator decoratedSimulator) {
        this.decoratedSimulator = decoratedSimulator;
    }

    @Override
    public int getFibonacciNumber(int index) throws IllegalArgumentException {
        Instant instStart = Instant.now();
        int result = decoratedSimulator.getFibonacciNumber(index);
        Instant instEnd = Instant.now();
        lastTimeMillis = Duration.between(instStart, instEnd).toMillis();
        return result;
    }

    public long getLastTimeMillis() {
        return lastTimeMillis;
    }
}
