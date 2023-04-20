package org.example.examples.quicksort;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class SorterTimeMeasureDecorator<T> implements Sorter<T> {

    private final Sorter<T> decoratedSorter;

    private long lastTimeSortingMillis;

    private long lastTimeIsSorted;

    public SorterTimeMeasureDecorator(Sorter<T> decoratedSorter) {
        this.decoratedSorter = decoratedSorter;
    }


    public long getLastTimeSortingMillis() {
        return lastTimeSortingMillis;
    }
    public long getLastTimeCheckingMillis() {
        return lastTimeIsSorted;
    }

    @Override
    public void sort(List<T> list) {
        Instant instStart = Instant.now();
        decoratedSorter.sort(list);
        Instant instEnd = Instant.now();
        lastTimeSortingMillis = Duration.between(instStart, instEnd).toMillis();
    }

    @Override
    public boolean isSorted(List<T> list) {
        Instant instStart = Instant.now();
        boolean result = decoratedSorter.isSorted(list);
        Instant instEnd = Instant.now();
        lastTimeIsSorted = Duration.between(instStart, instEnd).toMillis();
        return result;
    }
}
