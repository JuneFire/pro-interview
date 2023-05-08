package com.example;


import com.leetcode.offer.Offer1630;
import com.leetcode.offer.Offer315;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author zkCheng
 * @date 2023/4/3 10:19
 */
@SpringBootTest
public class offerTest {

    @Test
    public void test(){
        Offer315 solution = new Offer315();
//        System.out.println(solution.findRepeatNumber2(new int{} {4,3,1,2,5,2}));
        System.out.println(solution.maxProductAfterCutting(120));
    }

    @Test
    public void test1630(){
        Offer1630 offer1630 = new Offer1630();
        int[][] test = new int[][] {{1,11},{2,12},{3,13},{4,14},{5,15},{6,16},{7,17},{8,18},{9,19},{10,20}};
        String res = Arrays.toString(offer1630.spiralOrder(test));
        System.out.println(res);
    }

}
