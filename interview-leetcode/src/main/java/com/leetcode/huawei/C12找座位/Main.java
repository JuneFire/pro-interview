package com.leetcode.huawei.C12找座位;

import java.util.Scanner;
//
//public class Main.java {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        char[] seats = scanner.nextLine().toCharArray(); // 读取输入的座位信息并转换为字符数组
//        int maxAdditional = 0; // 最大额外观众数初始化为0
//
//        for (int i = 0; i < seats.length; i++) { // 遍历座位数组
//            if (seats[i] == '0' && (i == 0 || seats[i - 1] == '0') && (i == seats.length - 1 || seats[i + 1] == '0')) {
//                // 如果当前位置是空座且左侧或右侧也是空座，执行以下操作
//                maxAdditional++; // 最大额外观众数加1
//                seats[i] = '1';  // 将当前位置标记为已坐人
//                i++;  // 跳过下一个位置，因为已经坐人
//            }
//        }
//
//        System.out.println(maxAdditional); // 打印最大额外观众数
//    }
//
//}

public class Main {
    public static void main(String[] args) {
        int[] seats = {0, 1, 0, 0, 0, 1, 0};  // 输入的座位数组
        System.out.println(maxAudience(seats));
    }

    public static int maxAudience(int[] seats) {
        int res = 0;
        int n = seats.length;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 0) {
                if (i == 0 && seats[i+1] == 0) {
                    res += 1;
                    seats[i] = 1;
                } else if (i == n-1 && seats[i-1] == 0) {
                    res += 1;
                    seats[i] = 1;
                } else if (i > 0 && i < n-1 && seats[i-1] == 0 && seats[i+1] == 0) {
                    res += 1;
                    seats[i] = 1;
                }
            }
        }
        return res;
    }
}
