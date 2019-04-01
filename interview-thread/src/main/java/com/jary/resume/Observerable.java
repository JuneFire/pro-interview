package com.jary.resume;

public interface Observerable<T> {

    public void registerObserver(HrService o);

    public void removeObserver(HrService o);

    public void notifyObserver(String name, T t);

}
