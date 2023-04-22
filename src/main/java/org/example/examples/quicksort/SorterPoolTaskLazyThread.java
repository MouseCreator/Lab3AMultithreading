package org.example.examples.quicksort;
import java.util.Comparator;

public class SorterPoolTaskLazyThread<T> extends Thread {
    private final Partition<T> partition;
    private final Sortable<T> sortable;
    private final int from;
    private final int to;


    public SorterPoolTaskLazyThread(Sortable<T> list, Comparator<T> comparator, int from, int to, String name) {
        this.sortable = list;
        this.partition = new Partition<>(comparator);
        this.from = from;
        this.to = to;
        setName(name);
    }

    private void sortList(int from, int to) {
        if (from >= to)
            return;
        int pivot = partition.partition(sortable.list, from, to);
        sortList(from, pivot-1);
        sortList(pivot+1, to);
    }

    @Override
    public void run() {
        sortList(from, to);
    }
}
