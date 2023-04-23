package org.example.examples.mergesort;

import org.example.examples.quicksort.SharedSortedChecker;
import org.example.examples.quicksort.Sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortTwoThreads<T> implements Sorter<T> {
    private final Comparator<T> comparator;

    public MergeSortTwoThreads(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(List<T> list) {
        int mid = list.size()>>>1;
        List<T> list1 = new ArrayList<>(list.subList(0,mid));
        List<T> list2 = new ArrayList<>(list.subList(mid, list.size()));
        ListMerger<T> merger = new ListMerger<>(comparator);
        TwoThreadsRunnable<T> sortRunnable1 = new TwoThreadsRunnable<>(list1, comparator);
        TwoThreadsRunnable<T> sortRunnable2 = new TwoThreadsRunnable<>(list2, comparator);

        Thread thread1 = new Thread(sortRunnable1);
        Thread thread2 = new Thread(sortRunnable2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            list.sort(comparator);
        }
        merger.clearAndMerge(list,list1,list2);
    }

    @Override
    public boolean isSorted(List<T> list) {
        return new SharedSortedChecker<T>().isSorted(comparator, list);
    }
}
