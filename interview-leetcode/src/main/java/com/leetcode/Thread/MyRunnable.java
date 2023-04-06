package main.java.leetcode.Thread;

/**
 * @author zkCheng
 * @date 2021/11/16 16:31
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Runnable.....");
    }


    public static void main(String[] args){
        MyRunnable instance = new MyRunnable();
        Thread thread = new Thread(instance);
        thread.start();
    }
}
