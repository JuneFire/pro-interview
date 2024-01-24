package com.leetcode.huawei.C225部门人力分配;
/*题目描述部门在进行需求开发时需要进行人力安排。
        当前部门需要完成 N 个需求，需求用 requirements 表述，requirements[j] 表示第 i 个需求的工作量大小，单位：人月。
        这部分需求需要在 M 个月内完成开发，进行人力安排后每个月人力时固定的。
        目前要求每个月最多有2个需求开发，并且每个月需要完成的需求不能超过部门人力。
        请帮助部门评估在满足需求开发进度的情况下，每个月需要的最小人力是多少？
        输入描述
        输入为 M 和 requirements, M 表示需求开发时间要求，requirements 表示每个需求工作量大小，N 为 requirements长度，
        $\cdot\cdot\:1\leq N/2\leq M\leq N\leq10000$
        $\bullet \quad 1\leq \mathrm{requirements[ i] }\leq 10^{\Lambda}9$
        输出描述对于每一组测试数据，输出部门需要人力需求，行末无多余的空格
        用例
        输入
        3
        3 5 3 4

        输出
        6*/

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 创建Scanner对象用于读取输入
        Scanner sc = new Scanner(System.in);
        // 读取第一行输入，表示需求开发时间要求
        int m = Integer.parseInt(sc.nextLine());
        // 读取第二行输入，表示每个需求的工作量大小，并转换为整数数组
        int[] requirements = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 对需求工作量进行排序
        Arrays.sort(requirements);
        // 初始化二分查找的左边界为最大的需求工作量
        int left = requirements[requirements.length - 1];
        // 初始化二分查找的右边界为所有需求工作量之和除以最小月数加一
        int right = Arrays.stream(requirements).sum() / (m / 2) + 1;
        // 进行二分查找以确定最小人力
        while (left < right) {
            // 计算中间值
            int mid = left + (right - left) / 2;
            // 初始化所需月数
            int monthsNeeded = 0;
            // 遍历每个需求，判断是否可以在限定人力下完成
            for (int i = requirements.length - 1, j = 0; i >= j; --i) {
                // 如果当前需求大于中间人力值，则增加左边界
                if (requirements[i] > mid) {
                    left = mid + 1;
                    break;
                }
                // 如果当前和下一个需求之和大于中间人力值，或者只剩一个需求，则增加所需月数
                if (i == j || requirements[i] + requirements[j] > mid) {
                    monthsNeeded++;
                } else {
                    // 否则，将下一个需求也计算在当前月份内，并增加所需月数
                    j++;
                    monthsNeeded++;
                }
                // 如果所需月数大于限定月数，则增加左边界
                if (monthsNeeded > m) {
                    left = mid + 1;
                    break;
                }
            }
            // 如果所需月数小于等于限定月数，则减小右边界
            if (monthsNeeded <= m) {
                right = mid;
            }
        }
        // 输出最小人力需求
        System.out.println(left);
    }
}

