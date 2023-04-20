package org.example.examples.quicksort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class SorterPoolTask<T> extends RecursiveTask<T> {
    private final Comparator<T> comparator;
    private final List<T> list;
    private final int from;
    private final int to;
    public SorterPoolTask(List<T> list, Comparator<T> comparator, int from, int to) {
        this.list = list;
        this.comparator = comparator;
        this.from = from;
        this.to = to;
    }

    public SorterPoolTask(List<T> list, Comparator<T> comparator) {
        this.list = list;
        this.comparator = comparator;
        this.from = 0;
        this.to = list.size()-1;
    }


    private int partition(List<T> list, int from, int to) {
        T p = list.get(to);
        int i = from - 1;
        for (int j = from; j < to; ++j) {
            if (isLower(list.get(j), p)) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, ++i, to);
        return i;
    }

    private boolean isLower(T a, T b) {
        return comparator.compare(a, b) < 0;
    }

    private void sortList(int from, int to) {
        if (from >= to)
            return;
        int pivot = partition(list, from, to);
        ExecutorService executor = Executors.newCachedThreadPool();
        SorterPoolTask<T> task1 = new SorterPoolTask<>(list, comparator, from, pivot-1);
        SorterPoolTask<T> task2 = new SorterPoolTask<>(list, comparator, pivot+1, to);
        task2.fork();
        task1.compute();
        task2.join();

    }

    @Override
    protected T compute() {
        sortList(from, to);
        return null;
    }
}
