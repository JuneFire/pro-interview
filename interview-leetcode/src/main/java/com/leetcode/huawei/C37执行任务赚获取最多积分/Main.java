package com.leetcode.huawei.C37执行任务赚获取最多积分;


/*
题目描述现有N个任务需要处理，同一时间只能处理一个任务，处理每个任务所需要的时间固定为1。
        每个任务都有最晚处理时间限制和积分值，在最晚处理时间点之前处理完成任务才可获得对应的积分奖励。
        可用于处理任务的时间有限，请问在有限的时间内，可获得的最多积分。
        输入描述
        第一行为一个数 N, 表示有 N 个任务
        $\cdot1\leq N\leq100$
        第二行为一个数 T, 表示可用于处理任务的时间
        $\cdot1\leq T\leq100$
        接下来 N 行，每行两个空格分隔的整数 (SLA 和 V), SL A 表示任务的最晚处理时间，V 表示任务对应的积分。
        $\cdot 1\leq \mathrm{SLA\leq 100}$
        $\cdot0\leq V\leq100000$
        输出描述可获得的最多积分
*/

import java.util.*;

class Task {
    int deadline;
    int score;

    public Task(int deadline, int score) {
        this.deadline = deadline;
        this.score = score;
    }
}
// 这段代码首先读取输入的任务数量N和可用于处理任务的时间T。然后，它创建一个优先队列来存储所有的任务，任务按照积分从高到低，最晚处理时间从短到长的顺序排列。
// 接着，它遍历每个时间单位，每个时间单位内选择一个最优的任务来完成。最后，它输出总积分.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        PriorityQueue<Task> queue = new PriorityQueue<>((a, b) -> a.score != b.score ? b.score - a.score : a.deadline - b.deadline); //任务按照积分从高到低，最晚处理时间从短到长的顺序排列
        for (int i = 0; i < N; i++) {
            int deadline = sc.nextInt();
            int score = sc.nextInt();
            if(deadline <= T)
                queue.offer(new Task(deadline,score));
        }
        boolean[] visited = new boolean[T + 1];
        int totalScore = 0;
        while (!queue.isEmpty()) {
            Task task = queue.poll();
            for (int i = task.deadline; i > 0; i--) {  // 从最晚处理时间开始，找到一个未被访问过的时间点，完成任务
                if (!visited[i]) {
                    visited[i] = true;
                    totalScore += task.score;
                    break;
                }
            }
        }
        System.out.println(totalScore);
    }
}

