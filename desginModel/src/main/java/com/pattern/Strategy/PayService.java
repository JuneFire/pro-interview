package com.pattern.Strategy;

/**
 * @author zkCheng
 * @date 2021/12/3 10:40
 */
public interface PayService {

    /**
     * 支付接口
     * @param type 支付类型
     * @param amount 支付金额
     * @return
     */
    String pay(String type, String amount);
}



