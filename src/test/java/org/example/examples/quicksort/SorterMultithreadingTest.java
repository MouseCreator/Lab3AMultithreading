package org.example.examples.quicksort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SorterMultithreadingTest {

    @Test
    void sort() {
    }

    @Test
    void isSorted() {
        SorterMultithreading<Integer> sorter = new SorterMultithreading<>(Integer::compareTo);
        List<Integer> integerList = new ArrayList<>(IntStream.rangeClosed(0, 100)
                .boxed().toList());
        assertTrue(sorter.isSorted(integerList));
        Collections.swap(integerList, 10, 20);
        assertFalse(sorter.isSorted(integerList));
    }
}