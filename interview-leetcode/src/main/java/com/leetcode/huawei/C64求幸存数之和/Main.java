package com.leetcode.huawei.C64求幸存数之和;
/*
题目描述给一个正整数数列 nums, 一个跳数 jump, 及幸存数量 left。
        运算过程为：从索引0的位置开始向后跳，中间跳过 J 个数字，命中索引为 J+1 的数字，该数被敲出，并从该点起跳，以此类推，直到幸
        存 left 个数为止，然后返回幸存数之和。
        约束：
        · 0是第一个起跳点
        · 起跳点和命中点之间间隔 jump 个数字，已被敲出的数字不计入在内。
        · 跳到末尾时无缝从头开始 (循环查找),并可以多次循环。
        ·若起始时 left> len(nums) 则无需跳数处理过程。

        方法设计：
        * @param nums 正整数数列，长度范围[1, 10000] * @param jump 跳数，范围 [1, 10000]
        * @param left 幸存数量，范围 [0, 10000]
        * @return 幸存数之和
        一输入描述第一行输入正整数数列

        第二行输入跳数
        第三行输入幸存数量
        输出描述
        输出幸存数之和

1 2 3 4 5 6 7 8 9
4
3
*/

import com.leetcode.Mind.Sort;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] numStrs = sc.nextLine().split(" ");
        int jump = Integer.parseInt(sc.nextLine());
        int left = Integer.parseInt(sc.nextLine());
        Queue<Integer> queue = new LinkedList<>();
        for (String numStr : numStrs) {
            queue.offer(Integer.parseInt(numStr));
        }
        if(queue.size() <= left)
            System.out.println(queue.stream().mapToInt(Integer::intValue).sum());;
        queue.offer(queue.poll());
        int index = jump;
        while (queue.size() > left) {
//            index = (index + jump + 1) % queue.size();
            for (int i = 0; i < index; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        int sum = 0;
        while (!queue.isEmpty()) {
            sum += queue.poll();
        }
        System.out.println(sum);
    }

    /**
     * 计算幸存数之和
     * @param nums int整型一维数组 正整数数列，长度范围 [1,10000]
     * @param jump int整型 跳数，范围 [1,10000]
     * @param left int整型 幸存数量，范围 [0,10000]
     * @return long长整型
     */
    public long sumOfLeft (int[] nums, int jump, int left) {
        // write code here
        Queue<Integer> queue = new LinkedList<>();
        for (Integer num : nums) {
            queue.offer(num);
        }
        if(queue.size() <= left)
            return queue.stream().mapToInt(Integer::intValue).sum();
        queue.offer(queue.poll());
        int index = jump;
        while (queue.size() > left) {
//            index = (index + jump + 1) % queue.size();
            for (int i = 0; i < index; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        long sum = 0;
        while (!queue.isEmpty()) {
            sum += queue.poll();
        }
        return sum;
    }
}
