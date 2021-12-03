package com.pattern.Strategy;

import org.springframework.stereotype.Component;

/**
 * @author zkCheng
 * @date 2021/12/3 10:41
 *
 * 　2、对多重判断if-else语句的改造
 */
public interface PayStrategy {

    String MSG = "使用 %s ,消费了 %s 元";

    String pay(String type, String amount);
}









