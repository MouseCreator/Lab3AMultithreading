package org.example.examples.mergesort;

import org.example.examples.quicksort.SharedSortedChecker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TwoThreadsRunnable<T> implements Runnable {
    private final List<T> list;
    private final Comparator<T> comparator;

    public TwoThreadsRunnable(List<T> list, Comparator<T> comparator) {
        this.list=list;
        this.comparator=comparator;
    }
    void splitAndMerge(List<T> forList) {
        if (isSorted(forList))
            return;
        int mid = forList.size()>>>1;
        List<T> list1 = new ArrayList<>(forList.subList(0,mid));
        List<T> list2 = new ArrayList<>(forList.subList(mid, forList.size()));
        ListMerger<T> merger = new ListMerger<>(comparator);
        splitAndMerge(list1);
        splitAndMerge(list2);
        merger.clearAndMerge(forList, list1, list2);
    }
    @Override
    public void run() {
        splitAndMerge(list);
    }

    private boolean isSorted(List<T> list) {
        return new SharedSortedChecker<T>().isSorted(comparator, list);
    }
}
