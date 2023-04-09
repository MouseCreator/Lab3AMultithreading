package org.example.examples.fibonacci;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciForkJoinSequence implements FibonacciSimulator{
    @Override
    public int getFibonacciNumber(int index) throws IllegalArgumentException {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be a non-negative number: " + index + " < 0.");
        }
        int upperLimit = 38;
        if (index > upperLimit) {
            throw new IllegalArgumentException(String.format(
                    "Index must be less than %d to avoid long waits: %d > %d.", upperLimit, index, upperLimit));
        }
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        RecursiveTask<Integer> recursiveAction = new FibonacciRecursiveTask(index);
        Integer result = forkJoinPool.invoke(recursiveAction);
        forkJoinPool.shutdown();
        forkJoinPool.close();
        return result;
    }
}
