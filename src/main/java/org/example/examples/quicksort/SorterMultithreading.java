package org.example.examples.quicksort;

import java.util.Comparator;
import java.util.List;

public class SorterMultithreading<T> implements Sorter<T> {

    final Comparator<T> comparator;
    public SorterMultithreading(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    @Override
    public void sort(List<T> arr) {

    }
}
