package com.leetcode.huawei.C13转盘寿司;

import java.util.Stack;

/**
 题目描述
 寿司店周年庆，正在举办优惠活动回馈新老客户。
 寿司转盘上总共有 n 盘寿司，prices[i] 是第 i 盘寿司的价格，
 如果客户选择了第 i 盘寿司，寿司店免费赠送客户距离第 i 盘寿司最近的下一盘寿司 j, 前提是 prices[]]< prices[i], 如果没有满足条件的
 j, 则不赠送寿司。
 每个价格的寿司都可无限供应。
 输入描述
 输入的每一个数字代表每盘寿司的价格，每盘寿司的价格之间使用空格分隔，例如：
 3 15 6 14
 表示：
 ·第0 盘寿司价格 prices[0]为3
 ·第 1 盘寿司价格 prices[1]为 15 ·第 2 盘寿司价格 prices[2]为6 ·第 3 盘寿司价格 prices[3]为 14 ·寿司的盘数 n 范围为：$1\leq n\leq500$

 每盘寿司的价格 price 范围为：$1\leq $price$\leq 1000$
 输出描述输出
 享受优惠后的一组数据，每个值表示客户选择第 i 盘寿司时实际得到的寿司的总价格。使用空格进行分隔，
 例如：
 3 21 9 17

 注意，转盘是循环的
 */
public class Main {
    public static void main(String[] args) {
        int[] prices = {3, 15, 6, 14};
        int[] result = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < prices.length * 2; i++) {
            while (!stack.isEmpty() && prices[i % prices.length] < prices[stack.peek()]) {
                int index = stack.pop();
                result[index] += prices[i % prices.length];
            }
            stack.push(i % prices.length);
        }

        for (int price : result) {
            System.out.print(price + " ");
        }
    }
}
