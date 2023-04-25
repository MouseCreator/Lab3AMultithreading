package org.example.examples.quicksort;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class QuickSortThreadFactory<T> {
    private int threadsUsed;
    public boolean hasAvailableThread() {
        return threadsUsed < ForkJoinPool.getCommonPoolParallelism();
    }
    synchronized public Thread createThread(List<T> list, int from, int to) {
        threadsUsed++;
        return new Thread(new QuickSortRunnable<>(list, from, to));
    }
}
