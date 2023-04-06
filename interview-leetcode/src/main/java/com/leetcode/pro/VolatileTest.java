package main.java.leetcode.pro;

/**
 * @author zkCheng
 * @date 2021/11/9 18:02
 */
public class VolatileTest {

    public static volatile int race = 0;
    public static void increase() {
        race++;
    }
    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();

        }
        // 等待所有累加线程都结束
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(race);
    }


    // 双锁检测
    private static volatile VolatileTest singleInstance = null;

    public static VolatileTest getSingleInstance(){
        if(singleInstance == null){
            synchronized (VolatileTest.class){
                if(singleInstance == null){
                    singleInstance = new VolatileTest();
                }
            }
        }
        return singleInstance;
    }

}
