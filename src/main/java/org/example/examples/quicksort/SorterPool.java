package org.example.examples.quicksort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SorterPool<T> implements Sorter<T> {

    final Comparator<T> comparator;
    public SorterPool(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    @Override
    public void sort(List<T> list) {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        pool.invoke(new SorterPoolTask<>(list, comparator));

        pool.close();
    }

    @Override
    public boolean isSorted(List<T> list) {
        int poolSize = ForkJoinPool.getCommonPoolParallelism();
        int divide = list.size() / (poolSize - 1);
        int currentFrom = 0;
        SortedChecker<T> checker = new SortedChecker<>(list,comparator);
        for (int i = 0; i < poolSize; i++) {
            Runnable runnable = new IsSortedRunnable<>(checker, currentFrom, Math.min(currentFrom=currentFrom+divide,
                    list.size()-1));
            Thread thread = new Thread(runnable, "Is-Sorted-Thread-"+(i+1));
            thread.start();
        }
        return checker.getSortedFlag();
    }


}
