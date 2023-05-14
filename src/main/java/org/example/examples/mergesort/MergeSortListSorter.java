package org.example.examples.mergesort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortListSorter<T> {
    private final MergeThreadFactory<T> factory;
    private final ListMerger<T> merger;

    public MergeSortListSorter(Comparator<T> comparator) {
        this.merger = new ListMerger<>(comparator);
        this.factory = new MergeThreadFactory<>();
    }

    public void sort(List<T> list) {
        factory.restart();
        splitMerge(list);
    }

    void splitMerge(List<T> list) {
        int mid = list.size()>>>1;
        List<T> list1 = new ArrayList<>(list.subList(0,mid));
        List<T> list2 = new ArrayList<>(list.subList(mid, list.size()));
        if (factory.hasAvailableThread()) {
            Thread parallel = factory.createThread(this, list1);
            parallel.start();
            splitMerge(list2);
            try {
                parallel.join();
                merger.clearAndMerge(list,list1,list2);
            } catch (Exception e) {
                merger.librarySort(list);
            }
        } else {
            merger.splitMerge(list);
        }
    }
}
