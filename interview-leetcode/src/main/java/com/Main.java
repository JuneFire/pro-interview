package com;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
//        int[] nums = {21, 11, 45, 56, 9, 66, 77, 89, 78, 68, 100, 120, 111};
        int[] nums = {3,3,4};
        System.out.println(findElement(nums));
    }
// 然后
    static List<Integer> findElement(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        int[] rightMin = new int[n];
        leftMax[0] = nums[0];
        rightMin[n-1] = nums[n-1];
        for (int i = 1; i < n; i++) {  //第一次从左到右遍历，记录到当前位置为止的最大值；
            leftMax[i] = Math.max(leftMax[i-1], nums[i]);
        }
        for (int i = n-2; i >= 0; i--) { // 第二次从右到左遍历，记录到当前位置为止的最小值。
            rightMin[i] = Math.min(rightMin[i+1], nums[i]);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {  //再次遍历数组，如果某个位置的元素等于其左边的最大值和右边的最小值，那么这个元素就满足条件。
            if (nums[i] == leftMax[i] && nums[i] == rightMin[i]) {
                res.add(nums[i]);
            }
        }
        return res;
    }
}
