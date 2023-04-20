package org.example.examples.quicksort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class SorterMultithreading<T> implements Sorter<T> {

    final Comparator<T> comparator;
    public SorterMultithreading(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    @Override
    public void sort(List<T> list) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> result = executor.submit(new SorterTask<>(list, comparator));
        try {
            result.get();
        } catch (Exception e) {
            e.printStackTrace();
            list.sort(comparator);
        }
    }

    @Override
    public boolean isSorted(List<T> list) {
        ForkJoinPool pool = new ForkJoinPool(8);
        int poolSize = 8;
        int divide = list.size() / (poolSize - 1);
        int currentFrom = 0;
        SortedChecker<T> checker = new SortedChecker<>(list, comparator);

        for (int i = 0; i < poolSize; i++) {
            Runnable runnable = new IsSortedRunnable<>(checker, currentFrom, Math.min(currentFrom=currentFrom+divide,
                    list.size()-1));
            Thread thread = new Thread(runnable, "Is-Sorted-Thread-"+(i+1));
            thread.start();
        }
        pool.close();
        return checker.getSortedFlag();
    }


}
