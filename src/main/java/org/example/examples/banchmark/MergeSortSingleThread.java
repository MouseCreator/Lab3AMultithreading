package org.example.examples.banchmark;

import org.example.examples.quicksort.SharedSortedChecker;
import org.example.examples.quicksort.Sorter;

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
        List<T> list1 = list.subList(0,mid);
        List<T> list2 = list.subList(mid+1, list.size());

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
