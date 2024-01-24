package com.leetcode.huawei.C51寻找连续区间或数组连续和;
/*
题目描述
 给定一个含有N个正整数的数组，求出有多少个连续区间 (包括单个正整数),它们的和大丁等丁x。
        输入描述
        第一行两个整数N 和 x
        第二行有N个正整数 (每个正整数小于等于100)。
        输出描述
        输出一个整数，表示所求的个数。
*/

import java.util.*;

// 滑动窗口
// 通过使用滑动窗口的方法来解决。
// 我们可以维护一个窗口，窗口的左边界是i，右边界是j，窗口内的数字之和是sum。
// 初始时，i和j都是0，sum也是0。
// 然后，我们不断地向右移动j，直到sum大于等于x，
// 然后我们就找到了一个满足条件的连续区间。
// 然后，我们再不断地向右移动i，直到sum小于x，
// 然后我们就可以再次向右移动j。
// 我们重复这个过程，直到j到达数组的末尾。


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 数组长度
        int x = scanner.nextInt(); // 目标值

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = scanner.nextInt();

        int left = 0; // 滑动窗口的左端点
        int right = 0; // 滑动窗口的右端点
        int count = 0; // 记录连续区间个数
        int sum = 0; // 记录当前区间的和

        while (right < n) {
            sum += nums[right];

            while (sum >= x) {
                // 如果当前区间和大于等于x，那么以left为起点的所有连续区间都符合要求
                count += n - right; // 以left为起点的连续区间个数为n - right
                sum -= nums[left];
                left++;
            }

            right++;
        }

        System.out.println(count);
    }
}
