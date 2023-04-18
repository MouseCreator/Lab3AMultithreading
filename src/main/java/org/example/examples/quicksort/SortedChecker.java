package org.example.examples.quicksort;

import java.util.Comparator;
import java.util.List;

public class SortedChecker<T> {
    final List<T> list;
    final Comparator<T> comparator;
    public SortedChecker(List<T> list, Comparator<T> comparator) {
        this.list=list;
        this.comparator=comparator;
    }

    public boolean check(int from, int to) {
        for (int i = from; i < to; ++i) {
            if (isLower(list.get(i+1), list.get(i)))
                return false;
        }
        return true;
    }

    private boolean isLower(T a, T b) {
        return comparator.compare(a, b) < 0;
    }
}
