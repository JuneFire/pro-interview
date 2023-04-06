package com.designmodel.decorate.Impl;

import com.designmodel.decorate.Condiment;
import com.designmodel.decorate.MilkTea;

import java.math.BigDecimal;

/**
 * 红豆
 * @author zkCheng
 * @date 2023/3/27 15:29
 */
public class Ormosia implements Condiment {

    private MilkTea milkTea;

    public Ormosia(MilkTea milkTea){
        this.milkTea = milkTea;
    }

    @Override
    public String getDescription() {
        return milkTea.getDescription() + ",加红豆";
    }

    @Override
    public BigDecimal cost() {
        return milkTea.cost().add(new BigDecimal(2));
    }
}
