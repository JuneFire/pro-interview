package com.jary.resume;

public class ResumeFactory {

    public static ResumeDetailBO generatorResume(int resumeId){
        ResumeDetailBO resumeDetailBO = new ResumeDetailBO();
        resumeDetailBO.setName(""+resumeId);
        resumeDetailBO.setResumeId(resumeId);
        return resumeDetailBO;
    }

}
