package com.designmodel.decorate.Impl;

import com.designmodel.decorate.MilkTea;

import java.math.BigDecimal;

/**
 * @author zkCheng
 * @date 2023/3/27 15:23
 */
public class BoBoMilkTea implements MilkTea {

    @Override
    public String getDescription() {
        return "波波奶茶";
    }

    @Override
    public BigDecimal cost() {
        return new BigDecimal(8);
    }
}
