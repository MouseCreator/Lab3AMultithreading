package org.example.examples.fibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciConsistentSequenceTest {

    @Test
    void getFibonacciNumber() {
        int iterations = 10;
        FibonacciConsistentSequence sequence = new FibonacciConsistentSequence();
        int n0 = 0;
        int n1 = 1;
        for (int i = 0; i < iterations; i++) {
            assertEquals(n0, sequence.getFibonacciNumber(i));
            n1 = n0 + (n0=n1);
        }
    }
}