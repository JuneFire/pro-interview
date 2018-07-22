package com.jary.interview.bo;

import com.jary.interview.task.InterviewerTask;

import java.util.Timer;

public class InterviewerBO {

    /**
     * 面试官名称
     */
    private String name;

    /**
     * 处理简历时间
     * 单位:秒
     */
    private Integer dealInterviewTime;

    /**
     * 定时任务
     * 处理简历
     */
    private Timer timer;

    public InterviewerBO(String name,Integer dealInterviewTime){
        this.name = name;
        this.dealInterviewTime = dealInterviewTime;
        if (timer == null){
            timer = new Timer(false);
            timer.schedule(new InterviewerTask(this),1000,1000 * this.dealInterviewTime);
        }
    }

    public void cancelTimerTask(){
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }



    public String getName() {
        return name;
    }
}
