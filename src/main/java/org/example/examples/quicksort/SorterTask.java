package org.example.examples.quicksort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SorterTask<T> implements Runnable {
    private final Comparator<T> comparator;
    private final List<T> list;
    private final int from;
    private final int to;
    public SorterTask(List<T> list, Comparator<T> comparator, int from, int to) {
        this.list = list;
        this.comparator = comparator;
        this.from = from;
        this.to = to;
    }

    public SorterTask(List<T> list, Comparator<T> comparator) {
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

    private void sortList(List<T> list, int from, int to) {
        if (from >= to)
            return;
        int pivot = partition(list, from, to);
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> t1 = executor.submit(new SorterTask<>(list, comparator, from, pivot-1));
        sortList(list, pivot+1, to);
        try {
            t1.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        sortList(list, from, to);
    }
}
