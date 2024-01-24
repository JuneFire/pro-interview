package com.leetcode.huawei.C50求最多可以派出多少支团队;
/*
题目描述用数组代表每个人的能力一个比赛活动要求参赛团队的最低能力值为N 每个团队可以由一人或者两人组成 且一个人只能参加一个团队 计
        算出最多可以派出多少只符合要求的队伍。
        输入描述
        第一行代表总人数，范围1-500000
        第二行数组代表每个人的能力
        - 数组大小，范围1-500000
        -元素取值，范围1-500000第三行数值为团队要求的最低总能力值，范围1-500000

        输出描述
        最多可以派出的团队数量
*/
//这个问题可以通过排序和双指针来解决。
// 我们首先对能力数组进行排序，然后使用两个指针，一个指向数组的开始，一个指向数组的结束。
// 每次我们尝试将最弱和最强的人组成一个团队，如果他们的总能力满足要求，那么我们就可以派出一个团队，然后将两个指针都向中间移动。
// 如果他们的总能力不满足要求，那么我们只能将最强的人单独组成一个团队，然后将结束的指针向中间移动。我们重复这个过程，直到两个指针相遇

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] abilities = new int[n];
        for (int i = 0; i < n; i++) {
            abilities[i] = sc.nextInt();
        }
        int minAbility = sc.nextInt();
        Arrays.sort(abilities);
        int start = 0, end = n - 1;
        int count = 0;
        while (start <= end) {
            if(abilities[end] >= minAbility){
                end--;
                count++;
            }else {
                if (abilities[start] + abilities[end] >= minAbility) {
                    start++;
                    end--;
                    count++;
                } else {
                    end--;
                }
            }
        }
        System.out.println(count);
    }
}
