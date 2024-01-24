package com.leetcode.huawei.C213分月饼;
/*题目描述中秋节，公司分月饼，m个员工，买了n个月饼，m <= n,每个员工至少分 1个月饼，但可以分多个，
        单人分到最多月饼的个数是 Max1, 单人分到第二多月饼个数是 Max2 , Max1- Max2\le 3 ,
        单人分到第 n-1 多月饼个数是 Max(n-1), 单人分到第n多月饼个数是 Max(n), Max( n- 1) - Max( n)<=3，
        问有多少种分月饼的方法？
        输入描述
        每一行输入mn，表示m个员工，n个月饼，$m\leq n$
        输出描述
        输出有多少种月饼分法
        用例1
        输入
        2 4
        输出
        2
说明：
分法有2种：
4=1+3
4=2+2 注意 1+3 和 3+1 是一种分法
        */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(); // 读取员工数量m
        int n = sc.nextInt(); // 读取月饼数量n
        // 调用distribute方法并打印返回的分配方法总数
        System.out.println(distribute(m, n, n, 0, new int[m]));
        sc.close();
    }

    // 递归分配月饼的方法
    private static int distribute(int m, int remaining, int maxAllowed, int index, int[] distribution) {
        // 如果当前是最后一个员工
        if (index == m - 1) {
            // 检查剩余的月饼是否可以分配给最后一个员工
            // 并且满足最大和最小月饼数差值不超过3的条件
            if (remaining <= maxAllowed && (index == 0 || Math.abs(distribution[index - 1] - remaining) <= 3)) {
                return 1; // 如果满足条件，返回1种方法
            }
            return 0; // 如果不满足条件，返回0种方法
        }

        int ways = 0; // 初始化分配方法数为0
        // 确定当前员工可以分配的月饼数的起始值
        int start = (index == 0) ? 1 : distribution[index - 1] - 3;
        // 确定当前员工可以分配的月饼数的结束值
        int end = Math.min(remaining - (m - index - 1), maxAllowed);

        // 遍历所有可能的分配数量
        for (int i = start; i <= end; i++) {
            distribution[index] = i; // 尝试分配i个月饼给当前员工
            // 如果当前分配满足最大和最小月饼数差值不超过3的条件
            if (index == 0 || Math.abs(distribution[index - 1] - i) <= 3) {
                // 递归调用distribute方法，计算剩余员工和月饼的分配方法
                ways += distribute(m, remaining - i, i, index + 1, distribution);
            }
        }

        return ways; // 返回总的分配方法数
    }
}

