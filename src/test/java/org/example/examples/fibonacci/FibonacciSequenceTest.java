package org.example.examples.fibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciSequenceTest {
    @Test
    void testPositiveNumbers() {
        int iterations = 10;
        FibonacciMultiThreadSequence sequence = new FibonacciMultiThreadSequence();
        int n0 = 0;
        int n1 = 1;
        for (int i = 0; i < iterations; i++) {
            assertEquals(n0, sequence.getFibonacciNumber(i));
            n1 = n0 + (n0=n1);
        }
    }

    @Test
    void testNegativeNumbers() {
        FibonacciMultiThreadSequence sequence = new FibonacciMultiThreadSequence();
        assertThrows(IllegalArgumentException.class, ()->sequence.getFibonacciNumber(-1));
        assertThrows(IllegalArgumentException.class, ()->sequence.getFibonacciNumber(-1));
        assertThrows(IllegalArgumentException.class, ()->sequence.getFibonacciNumber(28));
        assertDoesNotThrow(()->sequence.getFibonacciNumber(0));
        assertDoesNotThrow(()->sequence.getFibonacciNumber(27));
    }
}