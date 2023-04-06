package com.designmodel.decorate.Impl;

import com.designmodel.decorate.MilkTea;

import java.math.BigDecimal;

/**
 * @author zkCheng
 * @date 2023/3/27 15:24
 */
public class HerbalJelly implements MilkTea {
    @Override
    public String getDescription() {
        return "烧仙草";
    }

    @Override
    public BigDecimal cost() {
        return new BigDecimal(7);
    }
}
