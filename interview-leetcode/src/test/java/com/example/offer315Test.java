package com.example;


import com.leetcode.Structure.TreeNode;
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

    @Test
    public void test3145(){
        Offer3145 off = new Offer3145();

//        System.out.println(off.validateStackSequences2(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1}));
//        off.maxSlidingWindow( new int[]{1,3,-1,-3,5,3,6,7}, 3);
        off.dicesProbability(2);
    }

    @Test
    public void test1(){
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);
        node.left = new TreeNode(3);
        Offer3145 off = new Offer3145();
        System.out.println(off.pathSum2(node, 3));
    }

    @Test
    public void test2(){
        Offer3145 off = new Offer3145();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = null;
        root.left.right = null;
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(off.serialize(root));

//        off.deserialize("1,2,3,null,null,4,5");
    }

    @Test
    public void test3(){
        Offer3145 offer = new Offer3145();
        offer.sortArray2(new int[]{1,2,3,4}, new int[]{2,4,6});
    }


}
