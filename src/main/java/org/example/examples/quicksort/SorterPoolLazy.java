package org.example.examples.quicksort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class SorterPoolLazy<T> implements Sorter<T> {
    final Comparator<T> comparator;
    public SorterPoolLazy(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    @Override
    public void sort(List<T> list) {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        pool.invoke(new SorterPoolTaskLazy<>(list, comparator));

        pool.close();
    }

    @Override
    public boolean isSorted(List<T> list) {
       return true;
    }
}
