package com.pattern.Strategy;

/**
 * @author zkCheng
 * @date 2021/12/3 10:46
 *
 * 5、Service调用PayStrategy的工厂类，返回PayStrategy的实现类
 */
public class PayStrategyFactory {

    /**
     * 根据type获取对应PayStrategy实现类
     * @param type
     * @return
     */
    public static PayStrategy getPayStrategy(String type){
        PayEnum payEnum = PayEnum.getByType(type);
        if (payEnum == null){
            return null;
        }
        return SpringContextUtil.getBean(payEnum.getBeanName(), PayStrategy.class);  //获取对应的bean对象
    }
}