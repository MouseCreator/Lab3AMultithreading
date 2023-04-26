package org.example.examples.mergesort;

import org.example.examples.quicksort.Sorter;

import java.util.Comparator;
import java.util.List;

public class MergeSortSingleThread<T> implements Sorter<T> {


    private final ListMerger<T> merger;

    public MergeSortSingleThread(Comparator<T> comparator) {
        this.merger = new ListMerger<>(comparator);
    }

    @Override
    public void sort(List<T> list) {
        splitAndMerge(list);
    }

    private void splitAndMerge(List<T> list) {
        merger.splitMerge(list);
    }

    @Override
    public boolean isSorted(List<T> list) {
        return merger.isSorted(list);
    }
}
