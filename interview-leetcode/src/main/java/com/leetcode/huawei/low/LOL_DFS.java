package com.leetcode.huawei.low;

import java.util.*;

public class LOL_DFS {
    static int res = Integer.MAX_VALUE;
    static int totalSum = 0;
    static int targetSum = 0;

    public static void main(String[] args) {
//        Scanner cin = new Scanner(System.in);
        String cin = "1 1 1 1 1 10000 10000 10000 10000 10000";
        int[] nums = Arrays.stream(cin.split(" "))
                .mapToInt(Integer::parseInt).toArray();
        for (int num : nums) {
            totalSum += num;
        }
        targetSum = totalSum / 2;
//        dfs(nums, 0, 0, 0, 0);
        dfs_my(nums, 0, 0, 0, 0);
        System.out.println(res);
//        cin.close();
    }

    // 深度递归 枚举所有可能性，最后判断5v5时，取最小差值
    static void dfs(int[] nums, int idx, int count1, int count2, int sum1) {
        // 剪枝条件：如果当前总和超过目标，则停止
        if (sum1 > targetSum) return;

        // 当我们为两个队伍选择了各自的5名玩家时
        if (count1 == 5 && count2 == 5) {
            // 计算另一个队伍的总和
            int sum2 = totalSum - sum1;
            // 用较小的差值更新结果
            res = Math.min(res, Math.abs(sum1 - sum2));
            return;
        }

        // 如果我们已经考虑了所有玩家，停止递归
        if (idx == 10) return;

        // 为第一个队伍选择当前玩家
        dfs(nums, idx + 1, count1 + 1, count2, sum1 + nums[idx]);

        // 为第二个队伍选择当前玩家
        dfs(nums, idx + 1, count1, count2 + 1, sum1);
    }

    //个人思考
    // 这道题，主要考察全匹配过程，将所有的结果进行排列，然后比对
     static void dfs_my(int[] nums, int idx, int count1, int count2, int sum1) {
         if (sum1 > targetSum) return;// 剪枝操作   当sum1大于  totalSum / 2 时，舍弃;

         if (count1 == 5 && count2 == 5) { // 满足要求 进行筛选
            int sum2 = totalSum - sum1;  // 总数减去 -sum1
            res = Math.min(res, Math.abs(sum1 - sum2));
            return;
        }

        if (idx == 10) return; // 超过10个人了，停止递归（回溯）

        dfs_my(nums, idx + 1, count1 + 1, count2, sum1 + nums[idx]); // 计算sum1

        dfs_my(nums, idx + 1, count1, count2 + 1, sum1); // 第二只队伍不需要冗余计算

    }

}
