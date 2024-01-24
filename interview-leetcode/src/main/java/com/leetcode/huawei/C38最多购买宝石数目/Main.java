package com.leetcode.huawei.C38最多购买宝石数目;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = scanner.nextInt();
        }

        int moneny = scanner.nextInt();

        int left = 0;
        int right = 0;
        int maxCount = 0;
        while (right < N && left <= right){
            if(compute(nums, left, right) <= moneny) {
                maxCount = Math.max(maxCount, right - left + 1);
                right++;
            }else {
                left++;
            }
        }
        System.out.println(maxCount);
    }

    public static int compute(int[] nums, int left, int right){
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return sum;
    }

}
