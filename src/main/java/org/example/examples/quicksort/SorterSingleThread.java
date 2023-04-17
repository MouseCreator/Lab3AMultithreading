package org.example.examples.quicksort;

import java.util.Comparator;
import java.util.List;

public class SorterSingleThread<T> implements Sorter<T>{

    final Comparator<T> comparator;
    public SorterSingleThread(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    @Override
    public void sort(List<T> arr) {

    }
}
