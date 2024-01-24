package com.leetcode.huawei.C212项目排期;
/*
题目描述项目组共有N个开发人员，项目经理接到了M个独立的需求，每个需求的工作量不同，且每个需求只能由一个开发人员独立完成，不能多
        人合作。假定各个需求直接无任何先后依赖关系，请设计算法帮助项目经理进行工作安排，使整个项目能用最少的时间交付。
        输入描述
        第一行输入为M个需求的工作量，单位为天，用逗号隔开。

        例如：X1 X2 X3 ... Xm 。表示共有M个需求，每个需求的工作量分别为X1天，X2天...Xm天。其中0<M<30;0<Xm<200
        第二行输入为项目组人员数量N 输出描述

        最快完成所有工作的天数
        用例
        输入：
        6 2 7 7 9 3 2 1 3 11 4
        2

        输出：
        28
        说明：
        共有两位员工，其中一位分配需求 627732 1共需要28天完成，另一位分配需求 93 11 4 共需要27天完成，故完成所有工作至少雪要28天。
*/

/**
 * 这是一个经典的负载均衡问题，
 * 可以使用优先队列（Java中的PriorityQueue）来解决。
 * 我们将所有的工作量放入一个大顶堆中，然后每次从堆顶取出工作量最大的任务分配给当前空闲的人。
 * 这样可以保证每次都是当前最大的工作量被分配出去，从而使得所有人的工作量尽可能均衡，从而最小化了完成所有工作的时间
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tasks = sc.nextLine().split(" ");
        int n = sc.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (String task : tasks) {
            queue.offer(Integer.parseInt(task));
        }
        int[] workers = new int[n];
        while (!queue.isEmpty()) {
            Arrays.sort(workers);
            workers[0] += queue.poll();
        }
        Arrays.sort(workers);
        System.out.println(workers[n - 1]);
    }
}
