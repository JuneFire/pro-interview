package com.designmodel.Observer;

/**
 * 观察者模式，一对多的关系，将观察者和被观察者分离，当被观察者发生变化时，通知所有观察者
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);  //add observer
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
