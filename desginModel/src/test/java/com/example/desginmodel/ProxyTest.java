package com.example.desginmodel;

import com.designmodel.proxy.*;
import org.junit.jupiter.api.Test;

/**
 * @author zkCheng
 * @date 2023/3/27 18:12
 */
public class ProxyTest {

    @Test
    public void test1(){
        // 公鸡
        Chicken cock = new Cock();
        cock.call();
        cock.behavior();
        System.out.println("=======================================");
        // ikun
        Chicken ikun = new IkunCock();
        ikun.call();
        ikun.behavior();
    }

    @Test
    public void test2(){
        // 公鸡
        Chicken cock = new ChickenProxy(new Cock());
        cock.call();
        cock.behavior();
        System.out.println("=======================================");
        // ikun
        Chicken ikun = new ChickenProxy(new IkunCock());
        ikun.call();
        ikun.behavior();
    }

    @Test
    public void test3(){
        // 公鸡
        Chicken cock = CustomLogInvocationHandler.createProxy(new Cock(), Chicken.class);
        cock.call();
        cock.behavior();
        System.out.println("=======================================");
        // ikun
        Chicken ikun = CustomLogInvocationHandler.createProxy(new IkunCock(), Chicken.class);
        ikun.call();
        ikun.behavior();
    }
}
