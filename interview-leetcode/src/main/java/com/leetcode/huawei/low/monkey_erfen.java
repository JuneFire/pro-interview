package com.leetcode.huawei.low;

import java.util.Arrays;
import java.util.Scanner;

// 二分法吃桃子
public class monkey_erfen {

    // 猴子吃桃速度太快就减，太慢就加
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入
        int[] peachTrees = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int H = scanner.nextInt();

        // 计算最小速度
        int result = minEatingSpeed(peachTrees, H);

        // 输出结果
        System.out.println(result);
    }

    /**
     *
     * @param peachTrees  桃树
     * @param H  守卫离开时间
     * @return
     */
    public static int  minEatingSpeed(int[] peachTrees, int H) {
        if (peachTrees.length > H) {
            return 0;
        }
        int max = peachTrees[0];
        for (int i = 0; i < peachTrees.length; i++) {
            max = Math.max(max, peachTrees[i]);
        }

        // 最大速度为max
        int min = 0;

        // 二分吃桃，找出最小吃桃速度
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (EatAll(peachTrees, H, mid)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    //能否在H小时内吃完
    public static boolean EatAll(int[] peachTrees, int H, int speed){
        int hours = 0;
        for (int peach : peachTrees) {
            hours += (peach + speed - 1) / speed;
        }
        return hours <= H;
    }
}
