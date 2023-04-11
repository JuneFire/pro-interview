package com.leetcode.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zkCheng
 * @date 2021/11/16 16:33
 */
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 520;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable instance = new MyCallable();
        //返回值通过 FutureTask 进行封装
        FutureTask<Integer> fk = new FutureTask<>(instance);
        Thread thread = new Thread(fk);
        thread.start();
        System.out.print(fk.get());
    }
}
