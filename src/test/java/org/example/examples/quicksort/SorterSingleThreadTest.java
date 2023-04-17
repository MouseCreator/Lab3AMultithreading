package org.example.examples.quicksort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SorterSingleThreadTest {

    @Test
    void sort() {
        SorterSingleThread<Integer> sorter = new SorterSingleThread<>(Integer::compareTo);
        List<Integer> integerList = new ArrayList<>(IntStream.rangeClosed(0, 100)
                .boxed().toList());
        Collections.shuffle(integerList);
        sorter.sort(integerList);
        assertTrue(sorter.isSorted(integerList));
    }
}