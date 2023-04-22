package org.example.examples.quicksort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Partition<T> {
    private final Comparator<T> comparator;

    public Partition(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    int partition(List<T> list, int from, int to) {
        T p0 = list.get(to);
        T p1 = list.get(from);
        T p2 = list.get((from+to+1)/2);

        T pivot;
        int pivotIndex;
        if (isLower(p0, p1) && isLower(p1, p2) || isLower(p2, p1) && isLower(p1, p0)) {
            pivot = p1;
            pivotIndex = from;
        } else if (isLower(p1, p0) && isLower(p0, p2) || isLower(p2, p0) && isLower(p0, p1)) {
            pivot = p0;
            pivotIndex = to;
        } else {
            pivot = p2;
            pivotIndex = (from+to+1)/2;
        }
        int i = from - 1;
        Collections.swap(list, pivotIndex, to);
        for (int j = from; j < to; ++j) {
            if (isLower(list.get(j), pivot)) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, ++i, to);
        return i;
    }

    /**
     * Compares two elements and returns true if first is strictly lower than second
     * @param a - first element to compare
     * @param b - second element to compare
     * @return true if a < b
     */
    public boolean isLower(T a, T b) {
        return comparator.compare(a, b) < 0;
    }
}
