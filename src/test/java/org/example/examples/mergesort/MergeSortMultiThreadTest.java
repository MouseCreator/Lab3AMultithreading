package org.example.examples.mergesort;

import org.example.examples.Sorter;
import org.example.examples.SorterTimeMeasureDecorator;
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
        int maxValue = 11;
        List<Integer> integerList = new ArrayList<>(IntStream.rangeClosed(0, maxValue)
                .boxed().toList());
        Collections.shuffle(integerList);
        sorter.sort(integerList);
        assertTrue(sorter.isSorted(integerList));
        assertEquals(maxValue + 1,integerList.size());
    }

    @Test
    void timeMeasure() {
        List<Integer> integerList = new ArrayList<>(IntStream.rangeClosed(0, 10000000).boxed().toList());
        SorterTimeMeasureDecorator<Integer> singleThread = new SorterTimeMeasureDecorator<>(
                new MergeSortSingleThread<>(Integer::compareTo));
        SorterTimeMeasureDecorator<Integer> multiThread = new SorterTimeMeasureDecorator<>(
                new MergeSortMultiThread<>(Integer::compareTo));
        SorterTimeMeasureDecorator<Integer> twoThreads = new SorterTimeMeasureDecorator<>(
                new MergeSortTwoThreads<>(Integer::compareTo));

        Collections.shuffle(integerList);
        executeAndCheck(singleThread, integerList);
        executeAndCheck(multiThread, integerList);
        executeAndCheck(twoThreads, integerList);

        System.out.println("Single thread = " + singleThread.getLastTimeSortingMillis() + " ms");
        System.out.println("Multi thread = " + multiThread.getLastTimeSortingMillis() + " ms");
        System.out.println("Two threads = " + twoThreads.getLastTimeSortingMillis() + " ms");
    }

    private void executeAndCheck(Sorter<Integer> sorter, List<Integer> list) {
        List<Integer> copyList = new ArrayList<>(list);
        sorter.sort(copyList);
        assertTrue(sorter.isSorted(copyList));
    }
}