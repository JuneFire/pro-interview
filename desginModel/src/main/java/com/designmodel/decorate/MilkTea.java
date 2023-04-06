package com.designmodel.decorate;

import java.math.BigDecimal;

/**
 * 装饰者模式
 * @author zkCheng
 * @date 2023/3/27 15:21
 */
public interface MilkTea {

    /**
     * 奶茶名称
     * @return
     */
    String getDescription();

    BigDecimal cost();
}
