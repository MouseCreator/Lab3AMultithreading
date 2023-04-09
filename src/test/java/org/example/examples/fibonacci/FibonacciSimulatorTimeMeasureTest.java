package org.example.examples.fibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciSimulatorTimeMeasureTest {
    @Test
    void testMultiThread() {
        int iterations = 10;
        FibonacciSimulatorTimeMeasure timeMeasure = new FibonacciSimulatorTimeMeasure(new FibonacciMultiThreadSequence());
        System.out.println("Multithreading");
        for (int i = 0; i < iterations; i++) {
            timeMeasure.getFibonacciNumber(i);
            System.out.println(i + " " + timeMeasure.getLastTimeMillis() + "ms");
        }
    }

    @Test
    void testConsistent() {
        int iterations = 10;
        FibonacciSimulatorTimeMeasure timeMeasure = new FibonacciSimulatorTimeMeasure(new FibonacciConsistentSequence());
        System.out.println("Consistent");
        for (int i = 0; i < iterations; i++) {
            timeMeasure.getFibonacciNumber(i);
            System.out.println(i + " " + timeMeasure.getLastTimeMillis() + "ms");
        }
    }
}