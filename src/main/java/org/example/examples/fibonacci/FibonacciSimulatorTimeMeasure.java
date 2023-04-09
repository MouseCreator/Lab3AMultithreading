package org.example.examples.fibonacci;

import org.apache.commons.lang3.time.StopWatch;

public class FibonacciSimulatorTimeMeasure implements FibonacciSimulator {
    private final FibonacciSimulator decoratedSimulator;
    
    private long lastTime;

    public FibonacciSimulatorTimeMeasure(FibonacciSimulator decoratedSimulator) {
        this.decoratedSimulator = decoratedSimulator;
    }

    @Override
    public int getFibonacciNumber(int index) throws IllegalArgumentException {
        StopWatch watch = new StopWatch();
        watch.start();
        int result = decoratedSimulator.getFibonacciNumber(index);
        watch.stop();
        lastTime = watch.getTime();
        return result;
    }

    public long getLastTime() {
        return lastTime;
    }
}
