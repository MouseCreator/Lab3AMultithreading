package org.example.examples.fibonacci;

import org.apache.commons.lang3.time.StopWatch;
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
       // StopWatch watch = new StopWatch();
        Instant instStart = Instant.now();
       // watch.start();
        int result = decoratedSimulator.getFibonacciNumber(index);
       // watch.stop();
        Instant instEnd = Instant.now();
        lastTimeMillis = Duration.between(instStart, instEnd).toMillis();
        return result;
    }

    public long getLastTimeMillis() {
        return lastTimeMillis;
    }
}
