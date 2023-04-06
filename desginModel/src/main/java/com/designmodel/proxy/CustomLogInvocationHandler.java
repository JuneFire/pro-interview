package com.designmodel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zkCheng
 * @date 2023/3/27 18:35
 */
public class CustomLogInvocationHandler implements InvocationHandler {

    private Object target;

    public CustomLogInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(this.target, args);
        long endTime = System.nanoTime();
        System.out.println(this.target.getClass().getSimpleName() + method.getName() + "方法耗时（纳秒）：" + (endTime - startTime));
        return result;
    }

    public static <T> T createProxy(Object target, Class<T> targetInterface){
        if(!targetInterface.isInterface()){
            throw new IllegalArgumentException("targetInterface 必须是接口类型");
        } else if (!targetInterface.isAssignableFrom(target.getClass())) {
            throw new IllegalStateException("target 必须是targetInterface的接口实现类");
        }
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new CustomLogInvocationHandler(target));
    }
}
