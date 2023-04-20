package org.example.examples.quicksort;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface Sorter<T> {
    void sort(List<T> list) throws ExecutionException, InterruptedException;

    boolean isSorted(List<T> list);
}
