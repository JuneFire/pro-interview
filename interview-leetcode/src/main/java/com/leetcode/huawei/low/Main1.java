package com.leetcode.huawei.low;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int[] reslut = removeSameNumAndSort(nums);
        for (int i : reslut) {
            System.out.println(i);
        }

    }

    public static int[] removeSameNumAndSort(int[] nums){
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
        }
        int[] numArray = new int[numSet.size()];
        int index = 0;
        for (Integer integer : numSet) {
            numArray[index++] = integer;
        }
        Arrays.sort(numArray);
        return numArray;

    }
}
