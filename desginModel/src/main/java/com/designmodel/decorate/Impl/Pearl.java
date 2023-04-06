package com.designmodel.decorate.Impl;

import com.designmodel.decorate.Condiment;
import com.designmodel.decorate.MilkTea;

import java.math.BigDecimal;

/**
 * 珍珠
 * @author zkCheng
 * @date 2023/3/27 15:29
 */
public class Pearl implements Condiment {

    private MilkTea milkTea;

    public Pearl(MilkTea milkTea){
        this.milkTea = milkTea;
    }

    @Override
    public String getDescription() {
        return milkTea.getDescription() + ",加珍珠";
    }

    @Override
    public BigDecimal cost() {
        return milkTea.cost().add(new BigDecimal(2));
    }
}
