package leetcode.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zkCheng
 * @date 2021/11/17 15:22
 */
public class LockExample {

    private Lock lock = new ReentrantLock();
    public void func(){
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }finally {
            System.out.println("end..");
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        LockExample example = new LockExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()-> example.func());
        executorService.execute(()-> example.func());

    }
}
