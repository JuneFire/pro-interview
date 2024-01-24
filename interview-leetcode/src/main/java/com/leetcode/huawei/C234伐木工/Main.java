package com.leetcode.huawei.C234伐木工;
/*
题目描述一根X米长的树木，伐木工切割成不同长度的木材后进行交易，交易价格为每根木头长度的乘积。规定切割后的每根木头长度都为正整
        数；也可以不切割，直接拿整根树木进行交易。
        请问伐木工如何尽量少的切割，才能使收益最大化？
        输入描述
        木材的长度 (X<50)
        输出描述
        输出最优收益时的各个树木长度，以空格分隔，按升序排列
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 创建一个扫描器来读取用户输入
        Scanner scanner = new Scanner(System.in);
        // 读取输入的长度
        int length = scanner.nextInt();
        // 调用getMaxProfit方法计算最大利润，并将结果存储在一个ArrayList中
        ArrayList<Integer> results = getMaxProfit(length);
        // 遍历结果并打印
        for (int i : results) {
            System.out.print(i + " ");
        }
    }

    private static ArrayList<Integer> getMaxProfit(int length) {
        // dp数组用于存储每个长度的最大乘积
        int[] dp = new int[length + 1];
        // cutTimes数组用于存储每个长度的最佳切割次数
        int[] cutTimes = new int[length + 1];
        // lastCut数组用于存储每个长度的最后一次切割长度
        int[] lastCut = new int[length + 1];

        // 遍历每个长度
        for (int i = 1; i <= length; i++) {
            // 初始化dp和lastCut数组
            dp[i] = lastCut[i] = i;
            // 遍历所有可能的切割长度
            for (int j = 1; j < i; j++) {
                // 计算当前切割长度的乘积
                int product = dp[i - j] * j;
                // 如果当前乘积大于已知的最大乘积，更新最大乘积和最佳切割长度
                if (product > dp[i]) {
                    lastCut[i] = j;
                    dp[i] = product;
                    cutTimes[i] = cutTimes[i - j] + 1;
                }
                // 如果当前乘积等于已知的最大乘积，但切割次数更少，更新最佳切割长度和切割次数
                else if (product == dp[i] && cutTimes[i] > cutTimes[i - j] + 1) {
                    lastCut[i] = j;
                    cutTimes[i] = cutTimes[i - j] + 1;
                }
            }
        }

        // 创建一个ArrayList来存储结果
        ArrayList<Integer> results = new ArrayList<>();
        // 从最大长度开始，每次减去最佳切割长度，直到长度为0
        while (length > 0) {
            // 将最佳切割长度添加到结果的开头
            results.add(0, lastCut[length]);
            // 更新长度
            length -= lastCut[length];
        }
        // 对结果进行排序
        Collections.sort(results);

        // 返回结果
        return results;
    }
}

