package org.example.examples.quicksort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SortedChecker<T> {
    final List<T> list;
    final Comparator<T> comparator;
    AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    public SortedChecker(List<T> list, Comparator<T> comparator) {
        this.list=list;
        this.comparator=comparator;
        clear();
    }

    public void check(int from, int to) {
        for (int i = from; i < to && atomicBoolean.get(); ++i) {
            if (isLower(list.get(i + 1), list.get(i))) {
                atomicBoolean.set(false);
                break;
            }
        }
    }

    public void clear() {
        this.atomicBoolean.set(true);
    }

    public boolean getSortedFlag() {
        return atomicBoolean.get();
    }

    private boolean isLower(T a, T b) {
        return comparator.compare(a, b) < 0;
    }
}
