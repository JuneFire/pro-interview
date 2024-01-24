package com.leetcode.huawei.C16游戏分组LOL;

import java.util.Arrays;

/**
 * 题目描述2020年题： 英雄联盟是一款十分火热的对战类游戏。每一场对战有10位玩家参与，分为两组，每组5人。每位玩家都有一个战斗力，代表着这位玩家
 *
 *  的厉害程度。为了对战尽可能精彩，我们需要把玩家们分为实力尽量相等的两组。一组的实力可以表示为这一组5位玩家的战斗力和。现在，给你10位玩家的战斗力，请你把他们分为实力尽量相等的两组。请你输出这两组的实力差。
 *
 *  2023年题：
 *  部门淮备举办一场王者荣耀表演赛，有10名游戏爱好者参与，分5为两队，每队5人。每位参与者都有一个评分，代表着他的游戏水平。
 *  为了表演赛尽可能精彩，我们需要把10名参赛者分为实力尽量相近的两队。一队的实力可以表示为这一队5名队员的评分总和。
 *  现在给你10名参与者的游戏水平评分，请你根据上述要求分队最后输出这两组的实力差绝对值。
 *  例：10名参塞者的评分分别为5 1 8 3 4 6 7 10 9 2, 分组为 (1 3 5 8 10) (2 4 6 7 9), 两组实力差最小，差值为1。有多种分法，但实力差的绝
 *  对值最小为1。
 *  输入描述10个整数，表示10名参与者的游戏水平评分。范围在[1,10000]之间
 *
 *  输出描述
 *  1个整数，表示分组后两组实力差绝对值的最小值.
 * 注意，每只队伍人数为5。 用java解决
 */

/**
 * 这个问题可以通过使用动态规划来解决。我们可以创建一个二维布尔数组 dp，其中 dp[i][j] 表示前 i 个玩家是否可以组成战斗力总和为 j 的一组。然后我们可以通过遍历所有的玩家和所有可能的战斗力总和来填充这个数组。最后，我们可以从总战斗力的一半开始向下查找，找到第一个 dp[5][j] 为 true 的 j，这就是两组战斗力最接近的情况。
 *
 * 以下是解决这个问题的步骤：
 *
 * 1. 计算所有玩家的战斗力总和 sum，并初始化二维布尔数组 dp，其中 dp[i][0] 为 true。
 * 2. 遍历所有的玩家和所有可能的战斗力总和，如果 dp[i-1][j] 为 true 或者 dp[i-1][j-players[i]] 为 true，那么 dp[i][j] 也为 true。
 * 3. 从 sum/2 开始向下查找，找到第一个 dp[5][j] 为 true 的 j，这就是两组战斗力最接近的情况，返回 sum - 2*j。

 */
//public class Main.java {
//    public static void main(String[] args) {
//        int[] players = {1, 1, 1, 1, 1, 100, 100, 100,100,100};
//        System.out.println(minDiff(players));
//    }
//    // * 这段代码首先计算了10位玩家的战斗力总和，并初始化了二维布尔数组 dp。然后通过遍历所有的玩家和所有可能的战斗力总和来填充这个数组。
//    // 最后，从总战斗力的一半开始向下查找，找到第一个 dp[5][j] 为 true 的 j，这就是两组战斗力最接近的情况，输出 sum - 2*j。
//    public static int minDiff(int[] players) {
//        int sum = Arrays.stream(players).sum();
//        boolean[][] dp = new boolean[6][sum + 1];
//        dp[0][0] = true;
//        for (int i = 1; i <= 10; i++) {
//            for (int j = 5; j >= 1; j--) {
//                for (int k = sum; k >= players[i - 1]; k--) {
//                    dp[j][k] = dp[j][k] || dp[j - 1][k - players[i - 1]];
//                }
//            }
//        }
//        for (int j = sum / 2; j >= 0; j--) {
//            if (dp[5][j]) {
//                return sum - 2 * j;
//            }
//        }
//        return -1;
//    }
//
//}

public class Main {
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[] players = {1, 1, 1, 1, 1, 100, 100, 100,100,100};
        dfs(players, 0, 0, 0, 0);
        System.out.println(minDiff);
    }

    public static void dfs(int[] players, int i, int total1, int total2, int num1) {
        if (i == players.length) {
            if (num1 == 5) {
                minDiff = Math.min(minDiff, Math.abs(total1 - total2));
            }
            return;
        }
        if (num1 < 5) {
            dfs(players, i + 1, total1 + players[i], total2, num1 + 1);
        }
        dfs(players, i + 1, total1, total2 + players[i], num1);
    }
}
/*
// DFS
public class Main.java {
    static int res = Integer.MAX_VALUE;
    static int totalSum = 0;
    static int targetSum = 0;

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int[] nums = Arrays.stream(cin.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        for (int num : nums) {
            totalSum += num;
        }
        targetSum = totalSum / 2;
        dfs(nums, 0, 0, 0);
        System.out.println(res);
        cin.close();
    }

    static void dfs(int[] nums, int idx, int count, int currentSum) {
        // 剪枝条件：如果当前总和超过目标，则停止
        if (currentSum > targetSum) return;

        // 当我们为一个队伍选择了5名玩家时
        if (count == 5) {
            // 计算另一个队伍的总和
            int otherTeamSum = totalSum - currentSum;
            // 用较小的差值更新结果
            res = Math.min(res, Math.abs(currentSum - otherTeamSum));
            return;
        }

        // 如果我们已经考虑了所有玩家，停止递归
        if (idx == 10) return;

        // 为第一个队伍选择当前玩家
        dfs(nums, idx + 1, count + 1, currentSum + nums[idx]);

        // 不为第一个队伍选择当前玩家
        dfs(nums, idx + 1, count, currentSum);
    }
}
*/
