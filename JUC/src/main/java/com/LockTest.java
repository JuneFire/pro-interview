package com;

/**
 * @author zkCheng
 * @date 2022/11/1 15:06
 */
public class LockTest {
    public static void main(String[] args){
        ReentrantLockTest test = new ReentrantLockTest();
        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    test.increment();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    test.decrement();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    test.increment();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    test.decrement();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
