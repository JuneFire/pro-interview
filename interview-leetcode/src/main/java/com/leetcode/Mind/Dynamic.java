package com.leetcode.Mind;

/**
 * @Author: zkcheng
 * @Date: 2021/06/30/20:10
 * @Description:
 */
public class Dynamic {

    public static void main(String[] args){
        Dynamic dynamic = new Dynamic();
        dynamic.robs(new int[]{1,2,3,2,4});
    }

    // 强盗抢劫，不能抢隔壁铺子的
    public int robs(int[] nums){
        int pre1 = 0, pre2 = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.max(pre2 + nums[i], pre1);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    //强盗抢劫，店铺是环形的，那么只能在第一家和最后一家做抉择
    public int robsQ(int [] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0 , n - 2), rob(nums, 1 , n - 1));
    }

    private int rob(int[] nums, int first, int last){
        int pre2 = 0, pre1 = 0;
        for (int i = first; i <= last; i++) {
            int cur = Math.max(pre2 + nums[i], pre1);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

}
