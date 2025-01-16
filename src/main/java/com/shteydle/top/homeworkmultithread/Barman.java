package com.shteydle.top.homeworkmultithread;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

import static com.shteydle.top.homeworkmultithread.Main.*;

public class Barman extends Thread{

    private Random random = new Random();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                while ((tobacco && paper) || (tobacco && flashing) || (paper && flashing)) {
                    try {
                        barmen.await();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
                int temp = random.nextInt(3);
                if (temp == 0) {
                    tobacco = false;
                    paper = true;
                    flashing = true;
                    System.out.println("Бармен кладет на стол бумагу и спички");
                    barmenAwait();
                    smokerTobacco.signal();
                }
                if (temp == 1) {
                    tobacco = true;
                    paper = false ;
                    flashing = true;
                    System.out.println("Бармен кладет на стол табак и спички");
                    barmenAwait();
                    smokerPaper.signal();
                }
                if (temp == 2) {
                    tobacco = true;
                    paper = true;
                    flashing = false;
                    System.out.println("Бармен кладет на стол бумагу и табак");
                    barmenAwait();
                    smokerFlashing.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private void barmenAwait() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
