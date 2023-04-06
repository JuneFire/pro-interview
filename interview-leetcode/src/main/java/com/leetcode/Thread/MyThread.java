package main.java.leetcode.Thread;

/**
 * @author zkCheng
 * @date 2021/11/16 16:37
 *
 * Java 不支持多重继承，因此继承了 Thread 类就无法继承其它类，但是可以实现多个接口；
 * 类可能只要求可执行就行，继承整个 Thread 类开销过大。
 */
public class MyThread extends Thread{
    public void run(){
        System.out.printf("Thread be extended");
    }

    public static void main(String[] args){
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
