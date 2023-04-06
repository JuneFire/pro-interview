package com.example;


import com.leetcode.offer.one.Offer315;
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

}
