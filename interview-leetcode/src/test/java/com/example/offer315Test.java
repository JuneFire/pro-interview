package com.example;


import com.leetcode.offer.Offer1630;
import com.leetcode.offer.Offer315;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zkCheng
 * @date 2023/4/3 10:19
 */
@SpringBootTest
public class offer315Test {

    @Test
    public void test(){
        Offer315 solution = new Offer315();
//        System.out.println(solution.findRepeatNumber2(new int[] {4,3,1,2,5,2}));
        System.out.println(solution.maxProductAfterCutting(120));
    }

    @Test
    public void test1630(){
        Offer1630 offer1630 = new Offer1630();
        System.out.println(offer1630.isNumber("e"));
    }
}
