package com.pattern.Strategy.Impl;

import com.pattern.Strategy.PayEnum;
import com.pattern.Strategy.PayStrategy;
import org.springframework.stereotype.Component;

/**
 * @author zkCheng
 * @date 2021/12/3 10:42
 */

@Component("aliPayStrategy")
public class AliPayStrategyImpl implements PayStrategy {

    @Override
    public String pay(String type, String amount) {
        return String.format(MSG, PayEnum.ALI_PAY.getDescription(), amount);
    }
}