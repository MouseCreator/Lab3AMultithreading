package org.example.examples.mergesort;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class ListMerger<T> {
    private final Comparator<T> comparator;

    public ListMerger(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void merge(List<T> result, List<T> list1, List<T> list2) {
        ListIterator<T> smallerIterator = list1.listIterator();
        ListIterator<T> biggerIterator = list2.listIterator();
        T smallerValue = smallerIterator.next();
        T biggerValue = biggerIterator.next();
        for (;;) {
            if (comparator.compare(smallerValue,biggerValue) < 0) {
                result.add(smallerValue);
                if (smallerIterator.hasNext())
                    smallerValue = smallerIterator.next();
                else {
                    result.add(biggerValue);
                    break;
                }
            } else {
                result.add(biggerValue);
                if (biggerIterator.hasNext())
                    biggerValue = biggerIterator.next();
                else {
                    result.add(smallerValue);
                    break;
                }
            }
        }
        while (biggerIterator.hasNext()) {
            biggerValue = biggerIterator.next();
            result.add(biggerValue);
        }
        while (smallerIterator.hasNext()) {
            smallerValue = smallerIterator.next();
            result.add(smallerValue);
        }
    }


    public void clearAndMerge(List<T> result, List<T> list1, List<T> list2) {
        result.clear();
        merge(result,list1,list2);
    }
}
