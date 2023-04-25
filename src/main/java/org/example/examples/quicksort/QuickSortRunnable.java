package org.example.examples.quicksort;

public class QuickSortRunnable<T> implements Runnable {


    private final ListQuickSorter<T> sorter;

    private final int from;
    private final int to;

    public QuickSortRunnable(ListQuickSorter<T> sorter, int from, int to) {
        this.sorter = sorter;
        this.from = from;
        this.to = to;
    }
    @Override
    public void run() {
        sorter.sortFromTo(from, to);
    }
}
