package org.example.examples.fibonacci;

import java.util.concurrent.RecursiveTask;

public class FibonacciRecursiveAction extends RecursiveTask<Integer> {
    private final int n;
    public FibonacciRecursiveAction(int index) {
        this.n = index;
    }
    @Override
    protected Integer compute() {
       return calculateInSeparateThread(n);
    }

    private int calculateInSeparateThread(int number) {
        if (number < 5) {
            return computeSimple(n);
        } else {
            FibonacciRecursiveAction action = new FibonacciRecursiveAction(number-2);
            action.fork();
            return calculateInSeparateThread(number-1) + action.join();
        }
    }

    private int computeSimple(int m) {
        if (m < 2) {
            return m;
        }
        return computeSimple(m-2) + computeSimple(m-1);
    }
}
