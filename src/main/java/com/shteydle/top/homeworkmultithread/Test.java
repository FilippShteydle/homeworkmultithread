package com.shteydle.top.homeworkmultithread;

import java.util.concurrent.Semaphore;

public class Test {

        // max 1 people
        static Semaphore semaphore = new Semaphore(2);

        static class MyLockerThread extends Thread {

            String name = "";

            MyLockerThread(String name) {
                this.name = name;
            }

            public void run() {

                try {

                    System.out.println(name + " : получение блокировки...");
                    System.out.println(name + " : теперь доступны разрешения на использование семафоров: "
                            + semaphore.availablePermits());

                    semaphore.acquire();
                    System.out.println(name + " : получил разрешение!");

                    try {

                        for (int i = 1; i <= 5; i++) {

                            System.out.println(name + " : выполняет операцию\n " + i
                                    + ", доступные разрешения на использование семафоров : "
                                    + semaphore.availablePermits());

                            // sleep 1 second
                            Thread.sleep(1000);

                        }

                    } finally {

                        // calling release() after a successful acquire()
                        System.out.println(name + " : разблокировка замка...");
                        semaphore.release();
                        System.out.println(name + " : теперь доступны разрешения на использование семафоров: "
                                + semaphore.availablePermits());

                    }

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            }

        }

        public static void main(String[] args) {

            System.out.println("Общее количество доступных разрешений на использование семафоров : "
                    + semaphore.availablePermits());

            MyLockerThread t1 = new MyLockerThread("Табак");
            t1.start();

            MyLockerThread t2 = new MyLockerThread("Бумага");
            t2.start();

            MyLockerThread t3 = new MyLockerThread("Спички");
            t3.start();

        }
}
