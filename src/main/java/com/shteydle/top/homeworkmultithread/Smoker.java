package com.shteydle.top.homeworkmultithread;

import java.util.concurrent.locks.ReentrantLock;

import static com.shteydle.top.homeworkmultithread.Main.*;


public class Smoker extends Thread {
    private String nameSmoker;
    private int id;

  public Smoker(String name, int id) {
      this.nameSmoker = name;
      this.id = id;
  }

    @Override
    public void run() {
      while (true) {
          try {
              lock.lock();
              switch (id) {
                  case 1 -> paperMethod();

                  case 2 -> tobaccoMethod();

                  case 3 -> flashingMethod();
              }
          } finally {
              lock.unlock();
          }
      }
    }

    private void flashingMethod() {
        while (!tobacco && !paper) {
            try {
                smokerFlashing.await();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Курильщик (" + nameSmoker + ") забирает табак и бумагу. Скручивает сигарету и курит (2с).");
        tobacco = false;
        flashing = false;
        paper = false;
        smokingSigarette();
        barmen.signal();
    }

    private void tobaccoMethod() {
        while (!paper && !flashing) {
            try {
                smokerTobacco.await();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Курильщик (" + nameSmoker + ") забирает бумагу и спички. Скручивает сигарету и курит (2с).");
        tobacco = false;
        flashing = false;
        paper = false;
        smokingSigarette();
        barmen.signal();
    }

    private void paperMethod() {
      while (!tobacco && !flashing) {
          try {
              smokerPaper.await();
          } catch (InterruptedException e) {
              System.out.println(e.getMessage());
          }
      }
        System.out.println("Курильщик (" + nameSmoker + ") забирает табак и спички. Скручивает сигарету и курит (2с).");
        tobacco = false;
        flashing = false;
        paper = false;
        smokingSigarette();
        barmen.signal();
    }

    private void smokingSigarette() {
      try {
          Thread.sleep(2000);
      } catch (InterruptedException e) {
          System.out.println(e.getMessage());
      }
    }
}
