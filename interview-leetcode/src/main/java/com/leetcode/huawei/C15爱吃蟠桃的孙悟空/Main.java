package com.leetcode.huawei.C15爱吃蟠桃的孙悟空;

// 二分查找
public class Main {
    public static void main(String[] args) {
        int[] peaches = {30, 11, 23, 4, 20};
        int H = 6;
        int K = minEatingSpeed(peaches, H);
        System.out.println(K);
    }

    public static int minEatingSpeed(int[] peaches, int H) {
        int low = 1;
        int high = 0;
        for (int peach : peaches) {
            high = Math.max(high, peach);
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canFinish(peaches, H, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return canFinish(peaches, H, low) ? low : 0;
    }

    private static boolean canFinish(int[] peaches, int H, int K) {
        int time = 0;
        for (int peach : peaches) {
            time += (peach - 1) / K + 1; // 向上取整就行
        }
        return time <= H;
    }
}
