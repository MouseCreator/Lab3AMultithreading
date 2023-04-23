package org.example.examples.mergesort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortMultiThreadTest {

    @Test
    void sort() {
        MergeSortMultiThread<Integer> sorter = new MergeSortMultiThread<>(Integer::compareTo);
        int maxValue = 99;
        List<Integer> integerList = new ArrayList<>(IntStream.rangeClosed(0, maxValue)
                .boxed().toList());
        Collections.shuffle(integerList);
        sorter.sort(integerList);
        assertTrue(sorter.isSorted(integerList));
        assertEquals(maxValue + 1,integerList.size());
    }
}