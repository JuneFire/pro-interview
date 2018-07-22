package com.jary.interview.producer;

import com.jary.interview.bo.HrObjectBO;
import lombok.extern.slf4j.Slf4j;

/**
 * 简历提供者
 */
@Slf4j
public class InterviewerProducer implements Runnable{

    @Override
    public void run() {
        log.info("开始提供简历.当前简历总数 = {},当前处理简历个数 = {}",HrObjectBO.getHrObject().getInterviewNum(),HrObjectBO.getHrObject().getDealInterviewNum());
        HrObjectBO.getHrObject().incInterviewNum();
        log.info("开始提供完毕.当前简历总数 = {},当前处理简历个数 = {}",HrObjectBO.getHrObject().getInterviewNum(),HrObjectBO.getHrObject().getDealInterviewNum());
    }
}
