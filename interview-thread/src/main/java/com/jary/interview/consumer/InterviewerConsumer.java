package com.jary.interview.consumer;


import com.jary.interview.bo.HrObjectBO;
import com.jary.interview.bo.InterviewerBO;
import lombok.extern.slf4j.Slf4j;

/**
 * 简历消费者
 */
@Slf4j
public class InterviewerConsumer implements Runnable{

    private InterviewerBO interviewerBO;

    public InterviewerConsumer(InterviewerBO interviewerBO){
        this.interviewerBO = interviewerBO;
    }

    @Override
    public void run() {
        synchronized (HrObjectBO.getHrObject().getReadWriteLock()) {
            // 先获得读锁
            HrObjectBO.getHrObject().getReadWriteLock().readLock().lock();
            log.info("处理简历开始,面试官 = {}.当前简历总数 = {},当前处理简历个数 = {}", interviewerBO.getName(), HrObjectBO.getHrObject().getInterviewNum(), HrObjectBO.getHrObject().getDealInterviewNum());
            // 获得简历总数
            Integer interviewNum = HrObjectBO.getHrObject().getInterviewNum();
            // 获得简历处理数
            Integer dealInterviewNum = HrObjectBO.getHrObject().getDealInterviewNum();
            if (interviewNum == null || dealInterviewNum == null) {
                log.info("获取简历数有问题.interviewNum = {},dealInterviewNum = {}", interviewNum, dealInterviewNum);
                HrObjectBO.getHrObject().getReadWriteLock().readLock().unlock();
                return;
            }
            Integer remainnInterviewNum = interviewNum.intValue() - dealInterviewNum.intValue();
            if (dealInterviewNum.intValue() >= HrObjectBO.MAX_INTERVIEW_NUM) {
                log.info("简历已经全部面试完毕.");
                HrObjectBO.getHrObject().getReadWriteLock().readLock().unlock();
                interviewerBO.cancelTimerTask();
                return;
            }
            if (remainnInterviewNum.intValue() <= 0) {
                log.info("面试官 {},收取简历时发现简历个数不足.", interviewerBO.getName());
                HrObjectBO.getHrObject().getReadWriteLock().readLock().unlock();
                return;
            }
            // 前置校验完成,释放读锁
            HrObjectBO.getHrObject().getReadWriteLock().readLock().unlock();
            // 获得写锁
            HrObjectBO.getHrObject().getReadWriteLock().writeLock().lock();
            HrObjectBO.getHrObject().incDealInterviewNum();
            // 释放写锁
            HrObjectBO.getHrObject().getReadWriteLock().writeLock().unlock();
            log.info("处理简历结束,面试官 = {}.当前简历总数 = {},当前处理简历个数 = {}", interviewerBO.getName(), HrObjectBO.getHrObject().getInterviewNum(), HrObjectBO.getHrObject().getDealInterviewNum());
            // 添加事件通知,题目要求里面需要通知

        }
    }
}
