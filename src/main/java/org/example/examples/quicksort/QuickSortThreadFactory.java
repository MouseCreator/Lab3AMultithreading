package org.example.examples.quicksort;

import java.util.concurrent.ForkJoinPool;

public class QuickSortThreadFactory<T> {
    private int threadsUsed;
    synchronized public boolean hasAvailableThread() {
        return threadsUsed < ForkJoinPool.getCommonPoolParallelism();
    }
    synchronized public Thread createThread(ListQuickSorter<T> listQuickSorter, int from, int to) {
        threadsUsed++;
        return new Thread(new QuickSortRunnable<>(listQuickSorter, from, to));
    }
}
