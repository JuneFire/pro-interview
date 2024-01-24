package com.leetcode.huawei.C69贪吃的猴子;
/*题目描述只贪吃的猴子，来到一个果园，发现许多串香蕉排成一行，每串香蕉上有若干根香蕉。每串香蕉的根数由数组numbers给出。
 猴子获取香蕉，每次都只能从行的开头或者末尾获取，并且只能获取N次，求猴子最多能获取多少根香蕉。
 输入描述
 第一行为数组numbers的长度
 第二行为数组numbers的值每个数字通过空格分开
 第三行输入为N，表示获取的次数
 备注：
 . $1\leq $numbers.length$\leq 100000$
 $\cdot\cdot1\leq numbers\leq100$
 $\cdot\cdot\:1\leq N\leq numbers.length$

输出描述按照题目要求能获取的最大数值*/

import java.util.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt(); // 读取数组长度
        int[] numbers = new int[len]; // 创建数组存储每串香蕉的数量
        for (int i = 0; i < len; i++) {
            numbers[i] = scanner.nextInt(); // 循环读取每串香蕉的数量
        }
        int N = scanner.nextInt(); // 读取猴子可以获取的次数
        System.out.println(maxBananas(numbers, N)); // 输出猴子能获取的最大香蕉数
    }

    // 定义方法计算猴子能获取的最大香蕉数
    private static int maxBananas(int[] numbers, int N) {
        int total = 0; // 初始化数组总和为0
        // 计算数组中所有香蕉的总数
        for (int number : numbers) {
            total += number;
        }

        // 如果N等于数组长度，猴子可以拿走所有的香蕉
        if (N == numbers.length) {
            return total;
        }

        int minWindowSum = Integer.MAX_VALUE; // 初始化最小窗口和为最大整数值
        int currentWindowSum = 0; // 初始化当前窗口和为0
        int windowSize = numbers.length - N; // 计算窗口大小

        // 初始化窗口的和
        for (int i = 0; i < windowSize; i++) {
            currentWindowSum += numbers[i];
        }
        minWindowSum = currentWindowSum; // 将当前窗口和赋值给最小窗口和

        // 通过滑动窗口计算最小窗口和
        for (int i = windowSize; i < numbers.length; i++) {
            // 窗口滑动，加上新进入窗口的元素，减去离开窗口的元素
            currentWindowSum += numbers[i] - numbers[i - windowSize];
            // 更新最小窗口和
            minWindowSum = Math.min(minWindowSum, currentWindowSum);
        }

        // 猴子能获取的最大香蕉数是总和减去最小窗口和
        return total - minWindowSum;
    }
}

