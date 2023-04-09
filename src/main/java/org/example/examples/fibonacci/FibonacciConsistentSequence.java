package org.example.examples.fibonacci;

public class FibonacciConsistentSequence implements FibonacciSimulator{
    @Override
    public int getFibonacciNumber(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be a non-negative number: " + index + " < 0.");
        }
        int upperLimit = 38;
        if (index > upperLimit) {
            throw new IllegalArgumentException(String.format(
                    "Index must be less than %d to avoid long waits: %d > %d.", upperLimit, index, upperLimit));
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
