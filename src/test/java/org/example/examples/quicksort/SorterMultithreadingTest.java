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
        SorterMultithreading<Integer> sorter = new SorterMultithreading<>(Integer::compareTo);
        List<Integer> integerList = new ArrayList<>(IntStream.rangeClosed(0, 100)
                .boxed().toList());
        Collections.shuffle(integerList);
        try {
            sorter.sort(integerList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        assertTrue(sorter.isSorted(integerList));
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

    @Test
    void timeMeasure() {
        SorterTimeMeasureDecorator<Integer> multithreadingSort = new SorterTimeMeasureDecorator<>(
                new SorterMultithreading<>(Integer::compareTo)
        );
        SorterTimeMeasureDecorator<Integer> singleThread = new SorterTimeMeasureDecorator<>(
                new SorterSingleThread<>(Integer::compareTo)
        );

        SorterTimeMeasureDecorator<Integer> poolThread = new SorterTimeMeasureDecorator<>(
                new SorterPool<>(Integer::compareTo)
        );

        List<Integer> integerList = new ArrayList<>(IntStream.rangeClosed(0, 1000)
                .boxed().toList());
        Collections.shuffle(integerList);

        executeAndCheck(multithreadingSort, integerList);
        executeAndCheck(singleThread, integerList);
        executeAndCheck(poolThread, integerList);

        System.out.println("Multithreading = " + multithreadingSort.getLastTimeSortingMillis() + "ms");
        System.out.println("Single thread = " + singleThread.getLastTimeSortingMillis() + "ms");
        System.out.println("Pool = " + poolThread.getLastTimeSortingMillis() + "ms");
    }

    private void executeAndCheck(Sorter<Integer> sorter, List<Integer> list) {
        List<Integer> copyList = new ArrayList<>(list);
        sorter.sort(copyList);
        sorter.isSorted(copyList);
    }
}