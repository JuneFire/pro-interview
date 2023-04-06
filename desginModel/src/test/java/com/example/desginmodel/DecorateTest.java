package com.example.desginmodel;

import com.designmodel.decorate.*;
import com.designmodel.decorate.Impl.BoBoMilkTea;
import com.designmodel.decorate.Impl.HerbalJelly;
import com.designmodel.decorate.Impl.Ormosia;
import com.designmodel.decorate.Impl.Pearl;
import com.designmodel.decorate.MilkTea;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zkCheng
 * @date 2023/3/27 15:25
 */
@SpringBootTest
public class DecorateTest {

    @Test
    public void test1(){
        MilkTea boBoMilkTea = new BoBoMilkTea();
        boBoMilkTea = new Pearl(boBoMilkTea);
        boBoMilkTea = new Ormosia(boBoMilkTea);
        System.out.println("饮料名称: " + boBoMilkTea.getDescription() + "    价格："+ boBoMilkTea.cost());

        MilkTea hebalJelly = new HerbalJelly();
        hebalJelly = new Ormosia(hebalJelly);
        hebalJelly = new Pearl(hebalJelly);
        System.out.println("饮料名称: " + hebalJelly.getDescription() + "    价格："+ hebalJelly.cost());

    }
}
