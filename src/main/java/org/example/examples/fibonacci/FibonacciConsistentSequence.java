package org.example.examples.fibonacci;

public class FibonacciConsistentSequence implements FibonacciSimulator{
    @Override
    public int getFibonacciNumber(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be a non-negative number: " + index + " < 0.");
        }
        if (index > 27) {
            throw new IllegalArgumentException("Index must be less than 27 to avoid long waits: " + index + " > 27.");
        }
        return calculateNumber(index);
    }
    private int calculateNumber(int m) {
        if (m < 2) {
            return m;
        }
        return calculateNumber(m-1) + calculateNumber(m-2);
    }
}
