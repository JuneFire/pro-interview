package com.leetcode.huawei.low;

import java.util.Scanner;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        // 输出目标整数T
        System.out.println(target + "=" + target);

        // 存储所有表达式的 vector
        List<String> expressions = new ArrayList<>();

        // 枚举从 1 开始的连续自然数的个数
        for (int i = 1; i < target; i++) {
            int sum = 0;
            StringBuilder sb = new StringBuilder();
            // 从第 i 个自然数开始累加
            for (int j = i; sum < target; j++) {
                sum += j;
                sb.append(j).append("+");
                // 找到了一个表达式
                if (sum == target) {
                    // 将表达式加入 vector
                    expressions.add(target + "=" + sb.substring(0, sb.length() - 1));
                    break;
                }
            }
        }

        // 按表达式中自然数的个数排序
        Collections.sort(expressions, Comparator.comparingInt(String::length));

        // 输出所有表达式
        for (String expression : expressions) {
            System.out.println(expression);
        }

        // 输出表达式的个数
        System.out.println("Result:" + (expressions.size() + 1));
    }
}
