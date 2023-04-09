package org.example.examples.fibonacci;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciForkJoinSequence implements FibonacciSimulator{
    @Override
    public int getFibonacciNumber(int index) throws IllegalArgumentException {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be a non-negative number: " + index + " < 0.");
        }
        if (index > 27) {
            throw new IllegalArgumentException("Index must be less than 27 to avoid long waits: " + index + " > 27.");
        }
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        RecursiveTask<Integer> recursiveAction = new FibonacciRecursiveTask(index);
        Integer result = forkJoinPool.invoke(recursiveAction);
        forkJoinPool.shutdown();
        forkJoinPool.close();
        return result;
    }
}
