package org.example.examples.quicksort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SorterTask<T> implements Runnable {
    private final Partition<T> partition;
    private final List<T> list;
    private final int from;
    private final int to;
    public SorterTask(List<T> list, Partition<T> partition, int from, int to) {
        this.list = list;
        this.partition = partition;
        this.from = from;
        this.to = to;
    }

    public SorterTask(List<T> list, Comparator<T> comparator) {
        this.list = list;
        this.partition = new Partition<>(comparator);
        this.from = 0;
        this.to = list.size()-1;
    }



    private void sortList(int from, int to) {
        if (from >= to)
            return;
        int pivot = partition.partition(list, from, to);
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> t1 = executor.submit(new SorterTask<>(list, partition, from, pivot-1));
        sortList(pivot+1, to);
        try {
            t1.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        sortList(from, to);
    }
}
