package com.jary.resume;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class HrServiceImpl implements HrService<ResumeDetailBO>,Runnable {

    private static final int MAX_NUM = 100;

    private static final AtomicInteger atomic = new AtomicInteger();

    private String name;

    private BlockingQueue<ResumeDetailBO> blockingQueue;

    public HrServiceImpl(String name,BlockingQueue<ResumeDetailBO> blockingQueue){

        this.name = name;
        this.blockingQueue = blockingQueue;

    }

    @Override
    public void receiveResume(ResumeDetailBO resumeDetailBO) {
        System.out.println("name = " + name + "收到简历,简历ID = " + resumeDetailBO.getResumeId());
        blockingQueue.offer(resumeDetailBO);
    }

    @Override
    public void updateResume(ResumeDetailBO resumeDetailBO) {
        System.out.println("简历ID = "+resumeDetailBO.getResumeId()+"处理完成");
    }

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            int time = random.nextInt(10);
            ResumeDetailBO resumeDetailBO = ResumeFactory.generatorResume(atomic.getAndIncrement());
            this.receiveResume(resumeDetailBO);
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            if (atomic.get() == MAX_NUM) break;
        }
    }

    @Override
    public void updateMessage(String name, ResumeDetailBO resumeDetailBO) {
        System.out.println("name = " + name + ",简历ID = " + resumeDetailBO.getResumeId() + "面试结果" + resumeDetailBO.getResumeStatus());
        this.updateResume(resumeDetailBO);
    }
}
