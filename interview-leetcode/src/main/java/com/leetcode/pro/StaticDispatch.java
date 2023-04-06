package main.java.leetcode.pro;

/**
 * @author zkCheng
 * @date 2021/11/5 13:42
 */
public class StaticDispatch {
    static abstract class Human {
    }
    static class Man extends Human {
    }
    static class Woman extends Human {
    }
    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }
    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }
    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        Woman woman1 = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello((Man) man);
        sr.sayHello((Woman) woman);
        sr.sayHello(woman1);
    }
}
