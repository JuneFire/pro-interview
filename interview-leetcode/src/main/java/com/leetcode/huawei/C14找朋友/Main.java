package com.leetcode.huawei.C14找朋友;

import java.util.Stack;

/**
 * 在学校中，N个小朋友站成一队，第i个小朋友的身高为height[i], 第i个小朋友可以看到的第一个比自己身高更高的小朋友j，那么j是i的好朋友(要求j>i)。
 *  请重新生成一个列表，对应位置的输出是每个小朋友的好朋友位置，如果没有看到好朋友，请在该位置用0代替。
 *  小朋友人数范围是[0, 40000]。
 *  输入描述
 *  第一行输入N，N表示有N个小朋友
 *  第二行输入N个小朋友的身高height[i], 都是整数
 *  输出描述
 *  输出N个小朋友的好朋友的位置
 */
public class Main {
    public static void main(String[] args) {
        int[] heights = {150, 160, 155, 170, 165};
        int[] result = findFriends(heights);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    public static int[] findFriends(int[] heights) {
        int n = heights.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] > heights[stack.peek()]) {
                int index = stack.pop();
                result[index] = i ;  // The position is 1-based
            }
            stack.push(i);
        }
        return result;
    }
}
