package com.leetcode.huawei.C19机器人仓库搬砖;
// 二分法
import java.util.*;

/**
 * 题目描述机器人搬砖，一共有N堆砖存放在N个不同的仓库中，第i堆砖中有bricks[i]块砖头，要求在8小时内搬完。机器人每小时能搬砖的数量取决于有多少能量格，机器人一个小时中只能在一个仓库中搬砖，机器人的能量格每小时补充一次且能量格只在这一个小时有效，为使得机器人损耗最小化尽量减小每次补充的能量格数,为了保障在8小时内能完成搬砖任务，请计算每小时给机器人充能的最小能量格数。
 *
 *  1、无需考虑机器人补充能量格的耗时，
 *  2、无需考虑机器人搬砖的耗时；
 *  3、机器人每小时补充能量格只在这一个小时中有效；
 *  输入描述
 *  第一行为一行数字，空格分隔
 *  输出描述
 *  机器人每小时最少需要充的能量格，若无法完成任务，输出-1
 */
public class Main {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String[] input = sc.nextLine().split(" ");
        String[] input = "30 12 25 8 19".split(" ");
        int[] bricks = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            bricks[i] = Integer.parseInt(input[i]);
        }
        //这个问题可以使用优先队列（PriorityQueue）来解决。我们可以将所有的砖块数放入一个最大堆中，它从堆中取出最大的砖块数，计算需要的能量格数，并将剩余的砖块数放回堆中。这样，我们就可以保证每小时搬运的砖块数尽可能小
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int brick : bricks) {
            pq.add(brick);
        }

        int hours = 8;
        int energy = 0;
        while (hours > 0) {
            int maxBricks = pq.poll();
            energy = Math.max(energy, (int) Math.ceil((double) maxBricks / hours));
            pq.add(maxBricks - energy);
            hours--;
        }

        System.out.println(energy);
    }
}

// 二分法

/*
public class Main.java {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] bricks = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            bricks[i] = Integer.parseInt(input[i]);
        }

        int left = 1, right = Arrays.stream(bricks).max().getAsInt();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(bricks, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(canFinish(bricks, left) ? left : -1);
    }

    private static boolean canFinish(int[] bricks, int energy) {
        int hours = 8;
        for (int brick : bricks) {
            hours -= (int) Math.ceil((double) brick / energy);
            if (hours < 0) {
                return false;
            }
        }
        return true;
    }
}*/
