package com.leetcode.huawei.C35最大N个数与最小N个数的和;
/*
题目描述给定一个数组，编写一个函数来计算它的最大N个数与最小N个数的和。你需要对数组进行去重。
        说明：
        · 数组中数字范围[0, 1000]
        · 最大N个数与最小N个数不能有重叠，如有$^{**}$重叠，输入非法$^{**}$返回-1
        ·输入非法返回-1
        输入描述
        ·第一行输入M，M标识数组大小
        ·第二行输入M个数，标识数组内容·第三行输入N，N表达需要计算的最大、最小N个数
*/


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int M =  scanner.nextInt(); // 数组大小
        int[] nums = new int[M];
        for (int i = 0; i < M; i++) {
            nums[i] = scanner.nextInt();
        }
        int N = scanner.nextInt(); // 最大、最小N个数
        if (N > M / 2) {
            System.out.println(-1);
            return;
        }

        Arrays.sort(nums);

        int[] max = new int[N];
        int[] min = new int[N];
        for (int i = 0; i < N; i++) {
            min[i] = nums[i];
            max[i] = nums[M - 1 - i];
        }

        int sum = Arrays.stream(max).sum() + Arrays.stream(min).sum();
        System.out.println(sum);
    }
}
