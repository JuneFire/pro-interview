package com.jary.interview;

import com.jary.interview.bo.HrObjectBO;
import com.jary.interview.bo.InterviewerBO;
import com.jary.interview.manager.ThreadPoolManager;

public class InterviewServer {

    public static void main(String[] args){
        // 初始化线程池
        ThreadPoolManager.initThreadPool();
        // 初始化HR对线
        HrObjectBO.init("HR");
        // 初始化面试官A
        new InterviewerBO("A",2);
        // 初始化面试官B
        new InterviewerBO("B",3);
        // 初始化面试官C
        new InterviewerBO("C",5);
        // 初始化面试官D
        new InterviewerBO("D",3);
    }

}
