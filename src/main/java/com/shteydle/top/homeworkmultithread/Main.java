package com.shteydle.top.homeworkmultithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static final int SMOKER_COUNT = 3;
    static boolean[] table = new boolean[3];
    static boolean tobacco = table[0];
    static boolean paper = table[1];
    static boolean flashing = table[2];

    static final Lock lock = new ReentrantLock();
    static final Condition barmen = lock.newCondition();
    static final Condition smokerTobacco = lock.newCondition();
    static final Condition smokerPaper = lock.newCondition();
    static final Condition smokerFlashing = lock.newCondition();



    public static void main(String[] args) {

        List<Smoker> smokers = new ArrayList<>();
        smokers.add(new Smoker("Табак", 2));
        smokers.add(new Smoker("Бумага", 1));
        smokers.add(new Smoker("Спички", 3));
        Barman barman = new Barman();


        for (int i = 0; i < smokers.size(); i++) {
            smokers.get(i).start();
        }
        barman.start();
    }
}
