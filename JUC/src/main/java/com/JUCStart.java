package com;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.TimeUnit;

/**
 * @author zkCheng
 * @date 2022/10/27 16:42
 */
public class JUCStart  {
   /* public static void main(String[] args) {
        Thread T1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t begin," +
                    (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            while (true)
            {

            }
        }, "t1");
        T1.setDaemon(true);
        T1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t ----end 主线程");

    }*/


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    System.out.println("第1次获取锁，这个锁是：" + this);
                    int index = 1;
                    while (true) {
                        synchronized (this) {
                            System.out.println("第" + (++index) + "次获取锁，这个锁是：" + this);
                        }
                        if (index == 10) {
                            break;
                        }
                    }
                }
            }
        }).start();
    }
}
