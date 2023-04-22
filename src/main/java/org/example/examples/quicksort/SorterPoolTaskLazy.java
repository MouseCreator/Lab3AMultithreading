package org.example.examples.quicksort;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SorterPoolTaskLazy<T> extends RecursiveTask<T> {
    private final Partition<T> partition;
    private final List<T> list;
    private final int from;
    private final int to;

    private final boolean isFirst;
    public SorterPoolTaskLazy(List<T> list, Partition<T> partition, int from, int to) {
        this.list = list;
        this.partition = partition;
        this.from = from;
        this.to = to;
        isFirst = false;
    }

    public SorterPoolTaskLazy(List<T> list, Comparator<T> comparator) {
        this.list = list;
        this.partition = new Partition<>(comparator);
        this.from = 0;
        this.to = list.size() - 1;
        isFirst = true;
    }

    private void sortList(int from, int to) {
        if (from >= to)
            return;
        int pivot = partition.partition (list, from, to);
        if (isFirst) {
            SorterPoolTaskLazy<T> task2 = new SorterPoolTaskLazy<>(list, partition, pivot + 1, to);
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
