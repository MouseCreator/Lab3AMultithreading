package org.example.examples.mergesort;

import org.example.examples.quicksort.SharedSortedChecker;
import org.example.examples.quicksort.Sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortSingleThread<T> implements Sorter<T> {

    private final Comparator<T> comparator;

    public MergeSortSingleThread(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(List<T> list) {
        splitAndMerge(list);
    }

    private void splitAndMerge(List<T> list) {
        if (isSorted(list))
            return;

        int mid = list.size()>>>1;
        List<T> list1 = new ArrayList<>(list.subList(0,mid));
        List<T> list2 = new ArrayList<>(list.subList(mid, list.size()));

        splitAndMerge(list1);
        splitAndMerge(list2);

        ListMerger<T> merger = new ListMerger<>(comparator);
        merger.clearAndMerge(list, list1, list2);
    }

    @Override
    public boolean isSorted(List<T> list) {
        return new SharedSortedChecker<T>().isSorted(comparator, list);
    }
}
