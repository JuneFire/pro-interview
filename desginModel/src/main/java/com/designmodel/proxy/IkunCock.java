package com.designmodel.proxy;

/**
 * @author zkCheng
 * @date 2023/3/27 18:06
 */
public class IkunCock implements Chicken{
    @Override
    public void call() {
        System.out.println("你干嘛。。。。");
    }

    @Override
    public void behavior() {
        System.out.println("唱跳rap篮球");
    }
}
