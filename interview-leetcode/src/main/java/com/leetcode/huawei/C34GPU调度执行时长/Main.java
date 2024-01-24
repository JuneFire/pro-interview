package com.leetcode.huawei.C34GPU调度执行时长;
/*

题目描述为了充分发挥GPU[算力],需要尽可能多的将任务交给GPU执行，现在有一个任务数组，数组元素表示在这1秒内新增的任务个数且每秒
        都有新增任务。
        假设GPU最多一次执行n个任务，一次执行耗时1秒，在保证GPU不空闲情况下，最少需要多长时间执行完成。
        输入描述
        ·第一个参数为GPU一次最多执行的任务个数，取值范围[1, 10000]
        ·第二个参数为任务数组长度，取值范围[1, 10000]
        ·第三个参数为任务数组，数字范围[1, 10000]

        输出描述执行完所有任务最少需要多少秒。
*/

import sun.security.krb5.internal.crypto.HmacSha1Aes128CksumType;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int maxtask = sc.nextInt(); // GPU一次最多执行的任务个数
        int tasklen = sc.nextInt(); // 任务数组长度
        int[] tasks = new int[tasklen]; // 任务数组

        for (int i = 0; i < tasklen; i++) {
            tasks[i] = sc.nextInt();
        }

        int curTask = 0; // 当前任务数
        int time = 0;
        for (int i = 0; i < tasklen; i++) {
            int task = tasks[i];
            curTask += task;

            if(curTask > maxtask){  // 当前任务不可以在1秒内执行完
                curTask -= maxtask;
                time++;
            }else {
                curTask = 0; // 当前任务可以在1秒内执行完成，那么当前任务数置为0
                time++;
            }
        }
        // 剩余任务数
        while (curTask > 0){
            curTask -= maxtask;
            time++;
        }
        System.out.println(time);
    }
}
