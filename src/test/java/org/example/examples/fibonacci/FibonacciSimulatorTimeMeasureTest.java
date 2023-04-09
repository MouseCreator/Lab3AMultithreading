package org.example.examples.fibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciSimulatorTimeMeasureTest {
    @Test
    void testMultiThread() {
        int iterations = 20;
        FibonacciSimulatorTimeMeasure timeMeasure = new FibonacciSimulatorTimeMeasure(new FibonacciMultiThreadSequence());
        System.out.println("Multithreading");
        measure(iterations, timeMeasure);
    }

    @Test
    void testConsistent() {
        int iterations = 36;
        FibonacciSimulatorTimeMeasure timeMeasure = new FibonacciSimulatorTimeMeasure(new FibonacciConsistentSequence());
        System.out.println("Consistent");
        measure(iterations, timeMeasure);
    }

    @Test
    void testForkJoin() {
        int iterations = 36;
        FibonacciSimulatorTimeMeasure timeMeasure = new FibonacciSimulatorTimeMeasure(new FibonacciForkJoinSequence());
        System.out.println("Fork-Join");
        measure(iterations, timeMeasure);
    }

    private static void measure(int iterations, FibonacciSimulatorTimeMeasure timeMeasure) {
        for (int i = 0; i < iterations; i++) {
            timeMeasure.getFibonacciNumber(i);
            System.out.println(i + " " + timeMeasure.getLastTimeMillis() + "ms");
        }
    }
}