package com.leetcode.huawei.C21寻找最富裕的小家庭;

import java.util.Scanner;

public class Main {
//    *
//     * 输入方式很不错，可以学习一下
//     * 4
//     * 100 200 300 400
//     * 1 2
//     * 1 3
//     * 2 3
//     * @param args
//
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 读取成员总数
        int[] wealth = new int[N + 1]; // 存储每个成员的财富值
        int[] familyWealth = new int[N + 1]; // 存储每个小家庭的财富和
        for (int i = 1; i <= N; i++) {
            wealth[i] = scanner.nextInt(); // 读取每个成员的财富值
            familyWealth[i] = wealth[i]; // 初始化每个小家庭的财富和
        }
        int maxWealth =  wealth[1] ; // 存储最大的财富和，如果只有一个成员，则最大财富和为该成员财富

        // 当成员数大于1时，才执行读取父子关系的循环
        for (int i = 1; i < N; i++) {
            int N1 = scanner.nextInt();
            int N2 = scanner.nextInt(); // 读取父子关系
            familyWealth[N1] += wealth[N2]; // 累加小家庭的财富和
            maxWealth = Math.max(maxWealth, familyWealth[N1]); // 更新最大的财富和
        }
        System.out.println(maxWealth); // 输出结果
    }



}


