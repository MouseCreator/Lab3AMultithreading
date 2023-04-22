package org.example.examples.quicksort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SorterPoolTaskLazy<T> extends RecursiveTask<T> {
    private final Comparator<T> comparator;
    private final List<T> list;
    private final int from;
    private final int to;

    private final boolean isFirst;
    public SorterPoolTaskLazy(List<T> list, Comparator<T> comparator, int from, int to) {
        this.list = list;
        this.comparator = comparator;
        this.from = from;
        this.to = to;
        isFirst = false;
    }

    public SorterPoolTaskLazy(List<T> list, Comparator<T> comparator) {
        this.list = list;
        this.comparator = comparator;
        this.from = 0;
        this.to = list.size()-1;
        isFirst = true;
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
        if (isFirst) {
            SorterPoolTaskLazy<T> task2 = new SorterPoolTaskLazy<>(list, comparator, pivot + 1, to);
            sortList(from, pivot - 1);
            task2.join();
        } else {
            sortList(from, pivot-1);
            sortList(pivot+1, to);
        }

    }

    @Override
    protected T compute() {
        sortList(from, to);
        return null;
    }
}
