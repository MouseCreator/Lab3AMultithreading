package org.example.examples.banchmark;

import org.example.examples.quicksort.SharedSortedChecker;
import org.example.examples.quicksort.Sorter;

import java.util.Comparator;
import java.util.List;

public class MergeSortSingleThread<T> implements Sorter<T> {

    private Comparator<T> comparator;
    @Override
    public void sort(List<T> list) {

    }

    @Override
    public boolean isSorted(List<T> list) {
        return new SharedSortedChecker<T>().isSorted(comparator, list);
    }
}
