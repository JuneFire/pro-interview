package com.leetcode.huawei.C44园区参观路径;
/*
题目描述
        园区某部门举办了Family Day， 邀请员工及其家属参加
        将公司园区视为一个矩形，起始园区设置在左上角，终点园区设置在右下角；
        家属参观园区时，只能向右和向下园区前进，求从起始园区到终点园区会有多少条不同的参观路径，
        输入描述
        第一行为园区的长和宽；
        后面每一行表示该园区是否可以参观，0表示可以参观，1表示不能参观
        输出描述
        输出为不同的路径数量
*/

/**
 * 这个问题可以使用动态规划来解决。我们可以创建一个二维数组dp，
 * 其中dp[i][j]表示从起始园区到位置(i, j)的不同参观路径数量。
 * 然后，我们可以遍历这个数组，如果位置(i, j)可以参观，那么dp[i][j]就等于dp[i-1][j]和dp[i][j-1]的和，
 * 因为我们只能从上面或者左边的园区到达位置(i, j)。
 * 如果位置(i, j)不能参观，那么dp[i][j]就等于0，因为我们不能到达这个位置.
 * 最后，dp[m-1][n-1]就是我们要的答案，其中m是园区的行数，n是园区的列数。
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0] == 0 ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] == 0 ? dp[i-1][0] : 0;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] == 0 ? dp[0][j-1] : 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        System.out.println(dp[m-1][n-1]);
    }
}
