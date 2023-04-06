package com.designmodel.proxy;

/**
 * @author zkCheng
 * @date 2023/3/27 18:06
 */
public class Cock implements Chicken{
    @Override
    public void call() {
        System.out.println("ooooooooo");
    }

    @Override
    public void behavior() {
        System.out.println("觅食");
    }
}
