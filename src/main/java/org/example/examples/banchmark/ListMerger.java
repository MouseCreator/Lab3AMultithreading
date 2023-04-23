package org.example.examples.banchmark;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class ListMerger<T> {
    private final Comparator<T> comparator;

    public ListMerger(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void merge(List<T> result, List<T> list1, List<T> list2) {
        if(list1.size() < list2.size())
            mergeLists(result, list1, list2);
        else
            mergeLists(result, list2, list1);
    }

    private void mergeLists(List<T> result, List<T> smaller, List<T> bigger) {
        ListIterator<T> smallerIterator = smaller.listIterator();
        ListIterator<T> biggerIterator = bigger.listIterator();
        T smallerValue = smallerIterator.next();
        T biggerValue = biggerIterator.next();
        for (;;) {
            if (comparator.compare(smallerValue,biggerValue) < 0) {
                result.add(smallerValue);
                if (smallerIterator.hasNext())
                    smallerValue = smallerIterator.next();
                else
                    break;
            } else {
                result.add(biggerValue);
                biggerValue = biggerIterator.next();
            }
        }
        for(;biggerIterator.hasNext();biggerValue = biggerIterator.next()){
            result.add(biggerValue);
        }
    }

    public void clearAndMerge(List<T> result, List<T> list1, List<T> list2) {
        result.clear();
        merge(result,list1,list2);
    }
}
