package org.example.examples.quicksort;

import java.util.List;

public interface Sorter<T> {
    void sort(List<T> list);

    boolean isSorted(List<T> list);
}