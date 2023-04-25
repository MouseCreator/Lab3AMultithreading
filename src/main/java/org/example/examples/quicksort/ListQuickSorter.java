package org.example.examples.quicksort;


import java.util.Comparator;
import java.util.List;

class ListQuickSorter<T> {
    private final QuickSortThreadFactory<T> factory;
    private final Partition<T> partition;
    private final List<T> list;

    public ListQuickSorter(List<T> list, Comparator<T> comparator) {
        this.list = list;
        this.factory = new QuickSortThreadFactory<>();
        this.partition = new Partition<>(comparator);
    }

    public void sort() {
        sortFromTo(0, list.size()-1);
    }

    void sortFromTo(int from, int to) {
        if (from >= to)
            return;
        if (factory.hasAvailableThread()) {
            int index = partition.partition(list, from, to);
            Thread parallel = factory.createThread(list, from, index-1);
            parallel.start();
            sortFromTo(index+1, to);
            try {
                parallel.join();
            } catch (Exception e) {
                partition.librarySort(list);
            }
        } else {
            partition.singleThreadSort(list, from, to);
        }
    }

}
