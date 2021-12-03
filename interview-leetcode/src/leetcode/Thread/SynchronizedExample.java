package leetcode.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zkCheng
 * @date 2021/11/17 15:18
 */
public class SynchronizedExample {

    /**
     * 使用 ExecutorService 执行了两个线程，由于调用的是同一个对象的同步代码块，因此这两个线程会进行同步，当一个线程进入同步语句块时，另一个线程就必须等待。
     */
    public void func1() {
        synchronized (this) { //synchronized (SynchronizedExample.class)
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    // 作用于整个类
    public synchronized static void fun() {
        // ...
    }



    public static void main(String[] args) {
        SynchronizedExample e1 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> e1.func1());
        executorService.execute(() -> e1.func1());
    }

}
