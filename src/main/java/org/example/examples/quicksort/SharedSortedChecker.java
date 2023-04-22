package org.example.examples.quicksort;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SharedSortedChecker<T> {
    public boolean isSorted(Comparator<T> comparator, List<T> list) {
        Iterator<T> iterator = list.iterator();
        if (!iterator.hasNext()) {
            return true;
        }
        T t = iterator.next();
        while (iterator.hasNext()) {
            T t2 = iterator.next();
            if (comparator.compare(t,t2) > 0) {
                return false;
            }
            t = t2;
        }
        return true;
    }
}
