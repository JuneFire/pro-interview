package com.leetcode.huawei.C238贪心歌手;
/*
题目描述一个歌手准备从A城去B城参加演出。

        1.按照合同，他必须在 T 天内赶到2. 歌手途经 N 座城市
        3. 歌手不能往回走
        4.每两座城市之间需要的天数都可以提前获知。
        5. 歌手在每座城市都可以在路边卖唱赚钱。

        经过调研，歌手提前获知了每座城市卖唱的收入预期：如果在一座城市第一天卖唱可以赚M，后续每天的收入会减少D (第二天赚的钱是 M-D, 第三天是 M-2D...)。如果收入减少到 0 就不会再少了。

        6.歌手到达后的第二天才能开始卖唱。如果今天卖过唱，第二天才能出发。

        贪心的歌手最多可以赚多少钱？ 输入描述
        第一行两个数字 T 和 N, 中间用空格隔开。

        · T代表总天数，$0<T<1000$ · N 代表路上经过 N 座城市，0<N< 100

        第二行 N+1 个数字，中间用空格隔开。代表每两座城市之间耗费的时间。·其总和$\leq T_{0}$
        接下来 N 行，每行两个数字 M 和 D, 中间用空格隔开。代表每个城市的输入预期。
        $\cdot0<M<1000$
        $\cdot0<D<100$
        输出描述一个数字。代表歌手最多可以赚多少钱。以回车结束。
*/

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // 总天数
        int N = scanner.nextInt(); // 城市数量
        int[] travelDays = new int[N + 1]; // 每两座城市之间耗费的时间
        for (int i = 0; i <= N; i++) {
            travelDays[i] = scanner.nextInt();
        }
        int[] M = new int[N]; // 每个城市的收入预期M
        int[] D = new int[N]; // 每个城市的收入递减值D
        for (int i = 0; i < N; i++) {
            M[i] = scanner.nextInt();
            D[i] = scanner.nextInt();
        }
        scanner.close();

        // 计算必须花费的路程时间
        int roadCost = 0;
        for (int i = 0; i <= N; i++) {
            roadCost += travelDays[i];
        }
        // 可用于卖唱赚钱的时间
        int remain = T - roadCost;

        // 使用优先队列记录每天的收益
        PriorityQueue<Integer> earningsQueue = new PriorityQueue<>();

        // 遍历每个城市
        for (int i = 0; i < N; i++) {
            int days = 0; // 当前城市卖唱的天数
            while (true) {
                int profitToday = Math.max(M[i] - days * D[i], 0);
                if (earningsQueue.size() < remain) {
                    earningsQueue.add(profitToday);
                } else {
                    if (!earningsQueue.isEmpty() && profitToday > earningsQueue.peek()) {
                        earningsQueue.poll(); // 移除最小收益
                        earningsQueue.add(profitToday); // 加入今天的收益
                    }
                }
                if (profitToday == 0) break; // 如果收益为0，不再卖唱
                days++;
            }
        }

        // 计算总收益
        int maxEarnings = 0;
        while (!earningsQueue.isEmpty()) {
            maxEarnings += earningsQueue.poll();
        }

        System.out.println(maxEarnings);
    }
}
