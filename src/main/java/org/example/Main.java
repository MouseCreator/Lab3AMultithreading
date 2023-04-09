package org.example;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Executor executor = new ScheduledThreadPoolExecutor(5);
    }
}