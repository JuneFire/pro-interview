package com;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zkCheng
 * @date 2022/11/1 15:06
 */
public class ReentrantLockTest {

    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    //condition.await(); // 等待
    //condition.signalAll(); // 唤醒全部
    //+1
    public void increment() {
        lock.lock();
        try {
            // 业务代码
            while (number != 0) { //0// 等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            // 通知其他线程，我+1完毕了
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    //-1
    public synchronized void decrement() {
        lock.lock();
        try {
            while (number == 0) { // 1
                // 等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            // 通知其他线程，我-1完毕了
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
