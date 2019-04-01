package com.jary.resume;

public interface InterviewService<T> extends Observerable<T> {

    public T receiveHrResume();

    public void doInterviewAction();

}
