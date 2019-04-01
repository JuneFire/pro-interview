package com.jary.resume;

public interface HrService<T> extends Observer<T>{

    public void receiveResume(T t);

    public void updateResume(T t);

}
