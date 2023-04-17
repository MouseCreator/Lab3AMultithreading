package org.example.examples.quicksort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SorterSingleThread<T> implements Sorter<T>{

    final Comparator<T> comparator;
    public SorterSingleThread(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    @Override
    public void sort(List<T> list) {
        sortList(list, 0, list.size()-1);
    }

    @Override
    public boolean isSorted(List<T> arr) {
        int size = arr.size() - 1;
        for (int i = 0; i < size; i++) {
            if (isLower(arr.get(i+1), arr.get(i)))
                return false;
        }
        return true;
    }

    private int partition(List<T> list, int from, int to) {
        T p = list.get(to);
        int i = from - 1;
        for (int j = from; j < to; ++j) {
            if (isLower(list.get(j), p)) {
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
    private boolean isLower(T a, T b) {
        return comparator.compare(a, b) < 0;
    }

    private void sortList(List<T> list, int from, int to) {
        if (from >= to)
            return;
        int pivot = partition(list, from, to);
        sortList(list, from, pivot-1);
        sortList(list, pivot+1, to);
    }
}
