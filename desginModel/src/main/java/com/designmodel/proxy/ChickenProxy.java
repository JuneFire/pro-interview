package com.designmodel.proxy;

/**
 * @author zkCheng
 * @date 2023/3/27 18:23
 */
public class ChickenProxy implements Chicken {
    // 目标对象，被代理的对象
    private Chicken target;

    public ChickenProxy(Chicken chicken){
        this.target = chicken;
    }

    @Override
    public void call() {
        long startTime = System.nanoTime();
        this.target.call();
        long endTime = System.nanoTime();
        System.out.println(this.target.getClass().getSimpleName() + "call方法耗时（纳秒）：" + (endTime - startTime));
    }

    @Override
    public void behavior() {
        long startTime = System.nanoTime();
        this.target.behavior();
        long endTime = System.nanoTime();
        System.out.println(this.target.getClass().getSimpleName() + "call方法耗时（纳秒）：" + (endTime - startTime));
    }
}
