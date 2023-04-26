package org.example.examples.mergesort;

import java.util.List;

class MergeSortRunnable<T> implements Runnable {
    private final MergeSortListSorter<T> listSorter;
    private final List<T> list;
    public MergeSortRunnable(MergeSortListSorter<T> listMergeSorter, List<T> list) {
        this.listSorter = listMergeSorter;
        this.list = list;
    }


    @Override
    public void run() {
        listSorter.splitMerge(list);
    }
}
