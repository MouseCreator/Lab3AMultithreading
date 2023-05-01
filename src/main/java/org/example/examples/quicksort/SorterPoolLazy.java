package org.example.examples.quicksort;

import org.example.examples.Sorter;

import java.util.Comparator;
import java.util.List;

public class SorterPoolLazy<T> implements Sorter<T> {
    final Comparator<T> comparator;
    public SorterPoolLazy(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    @Override
    public void sort(List<T> list) {
        int to = list.size()-1;
        Partition<T> partition = new Partition<>(comparator);
        int pivot = partition.partition(list, 0, to);
        SorterPoolTaskLazyThread<T> thread1 = new SorterPoolTaskLazyThread<>(list, comparator, 0, pivot-1, "Thread-0");
        SorterPoolTaskLazyThread<T> thread2 = new SorterPoolTaskLazyThread<>(list, comparator, pivot+1, to, "Thread-1");
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isSorted(List<T> list) {
        return new SharedSortedChecker<T>().isSorted(comparator, list);
    }
}
