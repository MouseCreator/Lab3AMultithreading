package org.example.examples.quicksort;

import org.example.examples.Sorter;

import java.util.Comparator;
import java.util.List;

public class LibSorter<T> implements Sorter<T> {
    private final Comparator<T> comparator;

    public LibSorter(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    @Override
    public void sort(List<T> list) {
        Partition<T> partition = new Partition<>(comparator);
        partition.librarySort(list);
    }

    @Override
    public boolean isSorted(List<T> list) {
        return new SharedSortedChecker<T>().isSorted(comparator, list);
    }
}
