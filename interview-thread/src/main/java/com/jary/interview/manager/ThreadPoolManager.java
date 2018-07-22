package com.jary.interview.manager;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolManager {

    private static final int CORE_POOL_SIZE = 20;

    private static final int MAX_IMUM_POOL_SIZE = 20;

    private static final long KEEP_ALIVE_TIME = 5;

    private static ThreadPoolManager threadPoolManager;

    public static ThreadPoolManager getThreadPoolManager(){
        return threadPoolManager;
    }

    public static void initThreadPool(){
        if (threadPoolManager == null){
            threadPoolManager = new ThreadPoolManager();
        }
    }

    private ExecutorService executorService;

    private ThreadPoolManager(){
        ThreadFactory threadFactory = new ThreadFactory() {

            private AtomicInteger atomicInteger = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"threadFactory " + atomicInteger.getAndIncrement());
            }
        };
        executorService = new ThreadPoolExecutor(ThreadPoolManager.CORE_POOL_SIZE,
                ThreadPoolManager.MAX_IMUM_POOL_SIZE,
                ThreadPoolManager.KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                threadFactory);

    }

    public void execute(Runnable runnable){
        executorService.execute(runnable);
    }

}
