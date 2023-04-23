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
        sorter.sort(integerList);

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
        SorterTimeMeasureDecorator<Integer> singleThread = new SorterTimeMeasureDecorator<>(
                new SorterSingleThread<>(Integer::compareTo)
        );

        SorterTimeMeasureDecorator<Integer> poolThread = new SorterTimeMeasureDecorator<>(
                new SorterPool<>(Integer::compareTo)
        );
        SorterTimeMeasureDecorator<Integer> lazyThread = new SorterTimeMeasureDecorator<>(
                new SorterPoolLazy<>(Integer::compareTo)
        );
        SorterTimeMeasureDecorator<Integer> librarySort = new SorterTimeMeasureDecorator<>(
                new LibSorter<>(Integer::compareTo)
        );

        List<Integer> integerList = new ArrayList<>(IntStream.rangeClosed(0, 150000)
                .boxed().toList());
        Collections.shuffle(integerList);

       executeAndCheck(librarySort, integerList);
       executeAndCheck(singleThread, integerList);
       executeAndCheck(poolThread, integerList);
       executeAndCheck(lazyThread, integerList);


        System.out.println("Single thread = " + singleThread.getLastTimeSortingMillis() + "ms");
        System.out.println("Thread Pool = " + poolThread.getLastTimeSortingMillis() + "ms");
        System.out.println("Two threads = " + lazyThread.getLastTimeSortingMillis() + "ms");
        System.out.println("Library = " + librarySort.getLastTimeSortingMillis() + "ms");
    }

    private void executeAndCheck(Sorter<Integer> sorter, List<Integer> list) {
        List<Integer> copyList = new ArrayList<>(list);
        sorter.sort(copyList);
        assertTrue(sorter.isSorted(copyList));
    }
}