package org.example.examples.quicksort;

import java.util.List;

public class Sortable<T> {
    volatile List<T> list;

    public Sortable(List<T> list) {
        this.list = list;
    }
}
