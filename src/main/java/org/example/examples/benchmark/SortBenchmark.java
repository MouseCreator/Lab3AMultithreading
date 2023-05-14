package org.example.examples.benchmark;

import org.example.examples.Sorter;
import org.example.examples.mergesort.MergeSortMultiThread;
import org.example.examples.mergesort.MergeSortSingleThread;
import org.example.examples.quicksort.SorterPool;
import org.example.examples.quicksort.SorterSingleThread;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SortBenchmark {
    public abstract static class SorterState {

        protected int N = 10000000;
        public Sorter<Integer> sorter;
        public List<Integer> list = new ArrayList<>();
        public void doSetUp() {
            list = new ArrayList<>(IntStream.rangeClosed(0, N).boxed().toList());
            Collections.shuffle(list);
        }
    }
    @State(Scope.Benchmark)
    public static class MergeSingleThread extends SorterState {
        public Sorter<Integer> sorter = new MergeSortSingleThread<>(Integer::compareTo);
        @Setup(Level.Iteration)
        public void doSetUp() {
            super.doSetUp();
        }
    }
    @State(Scope.Benchmark)
    public static class MergeMultiThread extends SorterState {
        public Sorter<Integer> sorter = new MergeSortMultiThread<>(Integer::compareTo);
        @Setup(Level.Iteration)
        public void doSetUp() {
            super.doSetUp();
        }
    }
    @State(Scope.Benchmark)
    public static class QuickSingleThread extends SorterState {
        public Sorter<Integer> sorter = new SorterSingleThread<>(Integer::compareTo);
        @Setup(Level.Iteration)
        public void doSetUp() {
            super.doSetUp();
        }
    }
    @State(Scope.Benchmark)
    public static class QuickMultiThread extends SorterState {
        public Sorter<Integer> sorter = new SorterPool<>(Integer::compareTo);
        @Setup(Level.Iteration)
        public void doSetUp() {
            super.doSetUp();
        }
    }
    @Benchmark
    @Measurement(iterations = 5, time = 10)
    public void sortQuickMulti(QuickMultiThread state) {
        state.sorter.sort(state.list);
    }

    @Benchmark
    @Measurement(iterations = 5, time = 10)
    public void sortQuickSingle(QuickSingleThread state) {
        state.sorter.sort(state.list);
    }

    @Benchmark
    @Measurement(iterations = 5, time = 10)
    public void sortMergeSingle(MergeSingleThread state) {
        state.sorter.sort(state.list);
    }
    @Benchmark
    @Measurement(iterations = 5, time = 10)
    public void sortMergeMulti(MergeMultiThread state) {
        state.sorter.sort(state.list);
    }
}
