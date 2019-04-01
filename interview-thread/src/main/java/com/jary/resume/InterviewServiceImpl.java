package com.jary.resume;

import java.util.*;
import java.util.concurrent.BlockingQueue;

public class InterviewServiceImpl implements InterviewService<ResumeDetailBO> ,Runnable{

    private List<HrService> observers;

    private String name;

    private long time;

    private Timer timer;

    private BlockingQueue<ResumeDetailBO> blockingDeque;

    private Queue<ResumeDetailBO> queue;

    public InterviewServiceImpl(String name, long time, BlockingQueue<ResumeDetailBO> blockingDeque, List<HrService> observers){
        this.name = name;
        this.time = time;
        this.queue = new LinkedList<>();
        this.observers = observers;
        this.blockingDeque = blockingDeque;
        timer = new Timer(true);
        timer.schedule(new InterviewTask(this),1000,1000 * this.time);
    }


    @Override
    public ResumeDetailBO receiveHrResume() {
        return blockingDeque.poll();
    }

    @Override
    public void doInterviewAction() {

        ResumeDetailBO resumeDetailBO = queue.poll();
        if (resumeDetailBO == null) return;
        Random random = new Random();

        int status = random.nextInt(2);
        resumeDetailBO.setResumeStatus(status == 1 ? "通过" : "未通过");
        this.notifyObserver(name,resumeDetailBO);

    }

    @Override
    public void registerObserver(HrService o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(HrService o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver(String name, ResumeDetailBO resumeDetailBO) {
        for (Observer observer : observers){
            observer.updateMessage(name,resumeDetailBO);
        }
    }

    @Override
    public void run() {
        while (true){
            ResumeDetailBO resumeDetailBO = receiveHrResume();
            if (resumeDetailBO == null) continue;
            queue.offer(resumeDetailBO);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static class InterviewTask extends TimerTask {

        private InterviewService interviewService;

        public InterviewTask(InterviewService interviewService){
            this.interviewService = interviewService;
        }

        @Override
        public void run() {
            interviewService.doInterviewAction();
        }
    }

}
