package org.example.examples.quicksort;

import java.util.List;

public class QuickSortRunnable<T> implements Runnable {


    private final List<T> list;

    private final int from;
    private final int to;

    public QuickSortRunnable(List<T> list, int from, int to) {
        this.list = list;
        this.from = from;
        this.to = to;
    }
    @Override
    public void run() {

    }
}
