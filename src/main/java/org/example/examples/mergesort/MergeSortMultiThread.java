package org.example.examples.mergesort;

import org.example.examples.quicksort.SharedSortedChecker;
import org.example.examples.Sorter;

import java.util.Comparator;
import java.util.List;

public class MergeSortMultiThread<T> implements Sorter<T> {
    private final Comparator<T> comparator;

    public MergeSortMultiThread(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(List<T> list) {
        MergeSortListSorter<T> auxiliarySorter = new MergeSortListSorter<>(comparator);
        auxiliarySorter.sort(list);
    }

    @Override
    public boolean isSorted(List<T> list) {
        return new SharedSortedChecker<T>().isSorted(comparator, list);
    }

}
