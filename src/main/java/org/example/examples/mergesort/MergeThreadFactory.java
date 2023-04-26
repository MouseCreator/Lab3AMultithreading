package org.example.examples.mergesort;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class MergeThreadFactory<T> {
    private int threadsUsed;
    synchronized public boolean hasAvailableThread() {
        return threadsUsed < ForkJoinPool.getCommonPoolParallelism();
    }
    synchronized public Thread createThread(MergeSortListSorter<T> listMergeSorter, List<T> list) {
        Thread result = new Thread(new MergeSortRunnable<>(listMergeSorter, list));
        result.setName("Merge-sort-thread-" + threadsUsed);
        ++threadsUsed;
        return result;
    }

    public void restart() {
        this.threadsUsed = 0;
    }
}
