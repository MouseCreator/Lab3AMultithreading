package org.example.examples.fibonacci;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FibonacciSequence {
    public int getFibonacciNumber(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be a non-negative number: " + index + " < 0.");
        }
        if (index > 58) {
            throw new IllegalArgumentException("Index must be less than 38 to avoid: " + index + " > 38.");
        }
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(new FibonacciTask(index));
        try {
            service.shutdown();
            service.close();
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
