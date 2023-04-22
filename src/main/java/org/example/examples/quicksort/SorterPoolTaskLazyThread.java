package org.example.examples.quicksort;
import java.util.Comparator;
import java.util.List;

public class SorterPoolTaskLazyThread<T> extends Thread {
    private final Partition<T> partition;
    private final List<T> list;
    private final int from;
    private final int to;


    public SorterPoolTaskLazyThread(List<T> list, Comparator<T> comparator, int from, int to, String name) {
        this.list = list;
        this.partition = new Partition<>(comparator);
        this.from = from;
        this.to = to;
        setName(name);
    }

    private void sortList(int from, int to) {
        if (from >= to)
            return;
        int pivot = partition.partition(list, from, to);
        sortList(from, pivot-1);
        sortList(pivot+1, to);
    }

    @Override
    public void run() {
        sortList(from, to);
    }
}
