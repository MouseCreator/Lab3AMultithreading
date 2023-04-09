package org.example.examples.fibonacci;

import java.util.concurrent.RecursiveTask;

public class FibonacciRecursiveTask extends RecursiveTask<Integer> {
    private final int n;
    public FibonacciRecursiveTask(int index) {
        this.n = index;
    }
    @Override
    protected Integer compute() {
       return calculateInSeparateThread(n);
    }

    private int calculateInSeparateThread(int number) {
        if (number < 5) {
            return computeSimple(number);
        } else {
            FibonacciRecursiveTask action = new FibonacciRecursiveTask(number-2);
            action.fork();
            int myTask = calculateInSeparateThread(number-1);
            return myTask + action.join();
        }
    }

    private int computeSimple(int m) {
        switch (m) {
            case 0 -> {
                return 0;
            }
            case 1, 2 -> {
                return 1;
            }
            case 3 -> {
                return 2;
            }
            case 4 -> {
                return 3;
            }
        }
        throw new IllegalStateException("Called simple calculation, when m is not small");
    }
}
