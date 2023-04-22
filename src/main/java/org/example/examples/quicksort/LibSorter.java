package org.example.examples.quicksort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class LibSorter<T> implements Sorter<T>{


    private final Comparator<T> comparator;

    public LibSorter(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    @Override
    public void sort(List<T> list) {
        list.sort(comparator);
        Arrays.sort(new int[3]);
    }

    @Override
    public boolean isSorted(List<T> list) {
        Iterator<T> iterator = list.iterator();
        T current, previous = iterator.next();
        while (iterator.hasNext()) {
            current = iterator.next();
            if (comparator.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}
