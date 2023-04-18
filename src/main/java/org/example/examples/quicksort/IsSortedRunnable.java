package org.example.examples.quicksort;

public class IsSortedRunnable<T> implements Runnable {
    private final SortedChecker<T> checker;

    private final int from;
    private final int to;

    public IsSortedRunnable(SortedChecker<T> checker, int from, int to) {
        this.checker = checker;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        checker.check(from, to);
    }
}
