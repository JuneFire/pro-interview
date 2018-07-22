package com.jary.interview.task;

import com.jary.interview.bo.HrObjectBO;
import com.jary.interview.bo.InterviewerBO;
import com.jary.interview.consumer.InterviewerConsumer;
import com.jary.interview.manager.ThreadPoolManager;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

@Slf4j
public class InterviewerTask extends TimerTask{

    /**
     * 面试官对象
     */
    private InterviewerBO interviewerBO;

    public InterviewerTask(InterviewerBO interviewerBO){
        this.interviewerBO = interviewerBO;
    }

    @Override
    public void run() {
        log.info("面试官 {},正在收取简历.当前简历总数 = {},当前处理简历个数 = {}",interviewerBO.getName(),HrObjectBO.getHrObject().getInterviewNum(),HrObjectBO.getHrObject().getDealInterviewNum());
        // 开始消费简历
        InterviewerConsumer consumer = new InterviewerConsumer(interviewerBO);
        ThreadPoolManager.getThreadPoolManager().execute(consumer);
    }
}
