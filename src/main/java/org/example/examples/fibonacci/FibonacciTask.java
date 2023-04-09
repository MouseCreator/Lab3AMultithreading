package org.example.examples.fibonacci;

import java.util.concurrent.*;

public class FibonacciTask implements Callable<Integer> {
    private final int n;
    public FibonacciTask(int n) {
        this.n = n;
    }
    @Override
    public Integer call() throws Exception {
        return calculate(n);
    }

    private int calculate(int m) {
        if (m < 2) {
            return 1;
        }
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(new FibonacciTask(n-2));
        try {
            service.shutdown();
            service.close();
            return calculate(m-1) + future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
