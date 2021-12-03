package com.pattern.Strategy;

import org.springframework.stereotype.Service;

/**
 * @author zkCheng
 * @date 2021/12/3 10:40
 *
 * 　主要是Service层代码有所改变。
 *
 * 　　1、Service层
 */
@Service
public class PayServiceImpl implements PayService {

    private static String MSG = "使用 %s ,消费了 %s 元";

    /**
     * 根据传入的类型，判断进入哪个bean，
     * @param type 支付类型
     * @param amount 支付金额
     * @return
     */
    @Override
    public String pay(String type, String amount) {

        PayStrategy payStrategy = PayStrategyFactory.getPayStrategy(type); // 根据类型获取对应的实现方法
        if (payStrategy == null) {
            return "输入的支付类型错误！";
        }
        return payStrategy.pay(type, amount);
    }


//    @Override
//    public String pay(String type, String amount) {
//        if (PayEnum.ALI_PAY.getType().equals(type)){
//            // 支付宝支付
//            return String.format(MSG, PayEnum.ALI_PAY.getDescription(), amount);
//        } else if (PayEnum.WECHAT_PAY.getType().equals(type)) {
//            // 微信支付
//            return String.format(MSG, PayEnum.WECHAT_PAY.getDescription(), amount);
//        } else if (PayEnum.UNION_PAY.getType().equals(type)) {
//            // 银联支付
//            return String.format(MSG, PayEnum.UNION_PAY.getDescription(), amount);
//        }
//        return "输入的支付类型错误！";
//    }
}