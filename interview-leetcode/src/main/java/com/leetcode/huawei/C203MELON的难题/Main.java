package com.leetcode.huawei.C203MELON的难题;
/*题目描述：MELON的难题 (本题200分) MELON有一堆精美的雨花石(数量为n，重量各异),准备送给S和W。MELON希望送给俩人的雨花石重量一致，请你设计一个程序，
        帮MELON确认是否能将雨花石平均分配。
        输入描述
        第1行输入为雨花石个数：n, 0<n<31.
        第2行输入为空格分割的各雨花石重量：m[0] m[1]... m[n-1], $0<m[k]<1001$
        不需要考虑异常输入的情况。
        输出描述
        如果可以均分，从当前雨花石中最少拿出几块，可以使两堆的重量相等：如果不能均分，则输出-1。
        用例1
        输入
        4
        1 1 2 2
        输出
        2
        说明

        输入第一行代表共4颗雨花石，第二行代表4颗雨花石重量分别为1、1、2、2。均分时只能分别为1，2,需要拿出重量为1和2的两块雨花石，所以输出2。*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] weights = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
            sum += weights[i];
        }
        if (sum % 2 != 0) {
            System.out.println(-1);
            return;
        }
        int target = sum / 2;

        // 创建动态规划数组，dp[i]表示前i块雨花石中是否能够取出一些雨花石使得重量和为j
        int[] dp = new int[target + 1];
        // 初始化dp数组，将除了dp[0]之外的其他元素设置为n，表示最坏情况下需要拿出所有雨花石
        for (int i = 1; i <= target; i++) {
            dp[i] = n;
        }
        // 遍历每一块雨花石
        for (int i = 1; i <= n ; i++) {
            int weight = weights[i - 1];  // 当前雨花石的重量
            //从目标重量开始递减，更新dp数组
            for (int j = target; j >= weight ; j--) {
                // 如果当前重量可以由前面的雨花石组成，更新为dp[j] 为最小需要拿出的雨花石数量
                dp[j] = Math.min(dp[j], dp[j - weight] + 1);
            }
        }
        // 如果dp[target]仍然等于n，表示无法找到满足条件的雨花石组合
        if(dp[target] == n){
            System.out.println(-1);
        }else {
            System.out.println(dp[target]);
        }
    }
}
