package com.example.abstractDemo;

/**
 * @author zkCheng
 * @date 2022/11/16 11:12
 */
// 父类继承爷爷类
public class Parent1 extends Old {

    private String eyes;


    @Override
    public void eat() {
        super.ear();
        super.tail();
        System.out.println("吃肉，通过哺乳其他动物");
    }

    @Override
    public void sleep() {
        System.out.println("困了就睡觉");
    }

    @Override
    public void weight() {
        System.out.println("比较大");
    }
}

