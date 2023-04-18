package org.example.examples.quicksort;

import java.util.Comparator;
import java.util.List;

public class SortedChecker<T> {
    final List<T> list;
    final Comparator<T> comparator;
    boolean sortedFlag;
    public SortedChecker(List<T> list, Comparator<T> comparator) {
        this.list=list;
        this.comparator=comparator;
        clear();
    }

    public void check(int from, int to) {
        for (int i = from; i < to && sortedFlag; ++i) {
            if (isLower(list.get(i + 1), list.get(i))) {
                sortedFlag = false;
                break;
            }
        }
    }

    public void clear() {
        this.sortedFlag = true;
    }

    public boolean getSortedFlag() {
        return sortedFlag;
    }

    private boolean isLower(T a, T b) {
        return comparator.compare(a, b) < 0;
    }
}
