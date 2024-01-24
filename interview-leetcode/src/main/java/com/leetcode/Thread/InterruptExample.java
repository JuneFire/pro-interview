package com.leetcode.Thread;

/**
 * @author zkCheng
 * @date 2021/11/17 14:56
 *
 * 通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，那么就会抛出 InterruptedException，从而提前结束该线程。
 * 但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
 *
 */
public class InterruptExample {
    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                while (!interrupted()) {
                    System.out.println("Thread !interrupted");
                    break;
                }
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread1();
        thread1.start();
        thread1.interrupt();
        System.out.println("Main.java run");



    }

}
