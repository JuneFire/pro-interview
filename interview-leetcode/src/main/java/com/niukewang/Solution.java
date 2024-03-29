package com.niukewang;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static List<List<Integer>> threenums(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if(i == 0 || (nums[i] != nums[i - 1])){
                int index1 = i+1, index2 = nums.length-1, sum = - nums[i];
                while (index1 < index2){
                    if(nums[index1] + nums[index2] == sum){
                        res.add(Arrays.asList(nums[index1], nums[index2], nums[i]));
                        while (index1 < index2 && nums[index1] == nums[index1++]) index1++;  //去重
                        while (index1 < index2 && nums[index2] == nums[index2 - 1]) index2--;
                        index1++;
                        index2--;
                    }else if(nums[index1] + nums[index2] < sum) {
                        index1++;
                    }else {
                        index2--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args){
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threenums(nums));
    }

}
     /*   编程题目：
        给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
                同时还满足 nums[i] + nums[j] + nums[k] == 0 。
                请 你返回所有和为 0 且不重复的三元组。
        注意：答案中不可以包含重复的三元组。

        示例 1：
        输入：nums = [-1,0,1,2,-1,-4]
        输出：[[-1,-1,2],[-1,0,1]]
        解释：
        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
        不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
        注意，输出的顺序和三元组的顺序并不重要。
        示例 2：
        输入：nums = [0,1,1]
        输出：[]
        解释：唯一可能的三元组和不为*/
