package org.example.examples.quicksort;

import java.util.concurrent.ForkJoinPool;

public class QuickSortThreadFactory<T> {
    private int threadsUsed;
    synchronized public boolean hasAvailableThread() {
        return threadsUsed < ForkJoinPool.getCommonPoolParallelism();
    }
    synchronized public Thread createThread(ListQuickSorter<T> listQuickSorter, int from, int to) {
        Thread result = new Thread(new QuickSortRunnable<>(listQuickSorter, from, to));
        result.setName("Quick-sort-thread-" + threadsUsed);
        ++threadsUsed;
        return result;
    }

    public void restart() {
        this.threadsUsed = 0;
    }
}
