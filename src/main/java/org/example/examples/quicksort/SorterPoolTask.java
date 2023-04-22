package org.example.examples.quicksort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class SorterPoolTask<T> extends RecursiveTask<T> {
    private final Partition<T> partition;
    private final List<T> list;
    private final int from;
    private final int to;
    public SorterPoolTask(List<T> list, Partition<T> partition, int from, int to) {
        this.list = list;
        this.partition = partition;
        this.from = from;
        this.to = to;
    }

    public SorterPoolTask(List<T> list, Comparator<T> comparator) {
        this.list = list;
        this.partition = new Partition<>(comparator);
        this.from = 0;
        this.to = list.size()-1;
    }

    private void sortList(int from, int to) {
        if (from >= to)
            return;
        int pivot = partition.partition(list, from, to);
        if (to - from < 33) {
            sortList(from, pivot-1);
            sortList(pivot+1, to);
        } else {
            SorterPoolTask<T> task2 = new SorterPoolTask<>(list, partition, pivot + 1, to);
            task2.fork();
            sortList(from, pivot - 1);
            task2.join();
        }

    }

    @Override
    protected T compute() {
        sortList(from, to);
        return null;
    }
}
