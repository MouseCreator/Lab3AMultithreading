package org.example.examples.quicksort;

import org.example.examples.Sorter;

import java.util.Comparator;
import java.util.List;

public class SorterSingleThread<T> implements Sorter<T> {


    final Partition<T> partition;
    public SorterSingleThread(Comparator<T> comparator) {
        this.partition = new Partition<>(comparator);
    }
    @Override
    public void sort(List<T> list) {
        partition.singleThreadSort(list,0,list.size()-1);
    }

    @Override
    public boolean isSorted(List<T> arr) {
        int size = arr.size() - 1;
        for (int i = 0; i < size; ++i) {
            if (partition.isLower(arr.get(i+1), arr.get(i)))
                return false;
        }
        return true;
    }

}
