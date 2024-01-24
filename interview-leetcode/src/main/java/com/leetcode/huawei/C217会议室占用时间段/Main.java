package com.leetcode.huawei.C217会议室占用时间段;
/*
题目描述现有若干个会议，所有会议共享- 个会议室，用数组表示各个会议的开始时间和结束时间，格式为：

        $1\mid[[会议1开始时间，会议1结束时间],[会议2开始时间，会议2结束时间]]$ 请计算会议室占用时间段。
        输入描述
        [[会议1开始时间，会议1结束时间],[会议2开始时间，会议2结束时间]]
        备注：

        ·会议室个数范围：[1,100] ·会议室时间段：[1,24]

        输出描述输出格式预输入一致，具体请看用例。

        $1\mid$【[会议开始时间，会议结束时间],[会议开始时间，会议结束时间]] 用例1
        输入：
        $1\mid[[1,4],[2,5],[7,9],[14,18]]$ 输出：
        [[1,5],[7,9],[14,18]]
*/



import java.util.Scanner;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] meetings = input.split("\\],\\[");
        meetings[0] = meetings[0].substring(2); // (0, end]
        meetings[meetings.length - 1] = meetings[meetings.length - 1].substring(0, meetings[meetings.length - 1].length() - 2);
        int[][] intervals = new int[meetings.length][2];
        for (int i = 0; i < meetings.length; i++) {
            String[] times = meetings[i].split(",");
            intervals[i][0] = Integer.parseInt(times[0].replaceAll("\\D", ""));
            intervals[i][1] = Integer.parseInt(times[1].replaceAll("\\D", ""));
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> result = new ArrayList<>();
        int[] currentInterval = intervals[0];
        result.add(currentInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= currentInterval[1]) {
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else {
                currentInterval = interval;
                result.add(currentInterval);
            }
        }
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
