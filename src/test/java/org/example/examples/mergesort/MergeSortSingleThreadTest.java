package org.example.examples.mergesort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MergeSortSingleThreadTest {

    @Test
    void sort() {
        MergeSortSingleThread<Integer> singleThreadSorter = new MergeSortSingleThread<>(Integer::compareTo);
        int maxValue = 99;
        List<Integer> integerList = new ArrayList<>(IntStream.rangeClosed(0, maxValue)
                .boxed().toList());
        Collections.shuffle(integerList);
        singleThreadSorter.sort(integerList);

        assertTrue(singleThreadSorter.isSorted(integerList));
        assertEquals(maxValue + 1,integerList.size());
    }


}