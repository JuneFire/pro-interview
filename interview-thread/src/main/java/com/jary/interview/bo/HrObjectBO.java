package com.jary.interview.bo;

import com.jary.interview.manager.ThreadPoolManager;
import com.jary.interview.producer.InterviewerProducer;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * HR对象
 */
@Slf4j
public class HrObjectBO {

    private static HrObjectBO hrObjectBO;

    public static final Integer MAX_INTERVIEW_NUM = 100;

    public static HrObjectBO getHrObject(){
        return hrObjectBO;
    }

    public static void init(String name){
        if (hrObjectBO == null){
            hrObjectBO = new HrObjectBO(name);
        }
    }

    /**
     * hr的姓名
     */
    private String name;

    /**
     * 简历数目
     */
    private Integer interviewNum;

    /**
     * 处理简历数目
     */
    private Integer dealInterviewNum;

    /**
     * 定时任务
     * 作用是生产简历
     */
    private Timer timer;

    private Object lock = new Object();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private HrObjectBO(String name){
        this.name = name;
        interviewNum = 0;
        dealInterviewNum = 0;
        if (timer == null){
            timer = new Timer(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    log.info("HR收集简历开始.");
                    if (interviewNum >= MAX_INTERVIEW_NUM){
                        log.info("简历已经收集满了,无须收集.");
                        //取消定时任务
                        cancelTimeTask();
                        return;
                    }
                    // 添加简历
                    InterviewerProducer producer = new InterviewerProducer();
                    ThreadPoolManager.getThreadPoolManager().execute(producer);
                    log.info("Hr收集简历结束.interviewNum = {},dealInterviewNum = {}", interviewNum, dealInterviewNum);
                }
            },1000,1000);
        }
    }

    /**
     * 收到简历数之后++
     * 直接使用关键字同步该方法
     * 主要是因为资源只有一个方法可以改变
     */
    public void incInterviewNum(){
        interviewNum++;
    }

    /**
     * 处理简历数之后++
     * 理由同上
     */
    public void incDealInterviewNum(){
        dealInterviewNum++;
    }

    /**
     * 获得Hr的名称
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 获得简历总数
     * @return
     */
    public Integer getInterviewNum() {
        synchronized (lock) {
            return interviewNum;
        }
    }

    /**
     * 获得已经处理数
     * @return
     */
    public Integer getDealInterviewNum() {
       synchronized (lock) {
           return dealInterviewNum;
       }
    }


    public void cancelTimeTask(){
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }

    public ReadWriteLock getReadWriteLock(){
        return readWriteLock;
    }

}
