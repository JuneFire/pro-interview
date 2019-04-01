package com.jary.resume;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ResumeMain {

    public static void main(String[] args){

        final BlockingQueue<ResumeDetailBO> blockingQueue = new ArrayBlockingQueue<ResumeDetailBO>(100);
        HrService hrService = new HrServiceImpl("HR", blockingQueue);
        List<HrService> observers = Arrays.asList(hrService);
        InterviewService interviewServiceA = new InterviewServiceImpl("A",3, blockingQueue, observers);
        InterviewService interviewServiceB = new InterviewServiceImpl("B",5, blockingQueue, observers);
        new Thread((Runnable) hrService).start();
        new Thread((Runnable) interviewServiceA).start();
        new Thread((Runnable) interviewServiceB).start();

    }

}
