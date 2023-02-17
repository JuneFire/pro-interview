package com;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zkCheng
 * @date 2022/11/1 15:32
 */
public class AtomicIntegerDemo {
    public static final int SIZE = 50;

    public static void main(String[] args) throws
            InterruptedException {
        AtomicIntegerDemo demo = new AtomicIntegerDemo();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for (int i = 0; i < SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 101; j++) {
                        demo.addPlusPlus();
                    }
                }finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t" + "result " + demo.atomicInteger.get());
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlusPlus() {
        atomicInteger.getAndIncrement();
    }
}
