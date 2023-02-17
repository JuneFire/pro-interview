import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zkCheng
 * @date 2022/1/14 13:26
 */
public class ThreadLocalTest {
    static ThreadLocal<Integer> tl = new ThreadLocal<>();
    public static void main(String[] args){

        //创建第一个线程
        Thread t1 = new Thread(() -> {
            tl.set(1);
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+ "-----" + tl.get());
                tl.set(tl.get() + 1);
            }
        },"Thread1");

        Thread t2 = new Thread(() -> {
            tl.set(100);
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+ "-----" + tl.get());
                tl.set(tl.get() - 1);
            }
        },"Thread2");

        t1.start();
        t2.start();

        tl.remove();  // 防止内存溢出
    }

    private static final Lock lock = new ReentrantLock();
}
