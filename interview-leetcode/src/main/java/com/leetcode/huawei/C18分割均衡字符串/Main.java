package com.leetcode.huawei.C18分割均衡字符串;

import java.util.Scanner;

/**
 * 题目描述均衡串定义：字符串只包含两种字符, 且两种字符的个数相同。
 * 给定一个均衡字符串，请给出可分割成新的均衡子串的最大个数。
 * 约定字符串中只包含大写的X和Y两种字符。
 * 输入描述 均衡串：XXYYXY 字符串的长度[2,100001]。给定的字符串均为均衡串 输出描述 可分割为两个子串： XXYY XY 备注分割后的子串，是原字符串的连续子串
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int countX = 0, countY = 0, result = 0;

        for (char c : str.toCharArray()) {
            if (c == 'X') {
                countX++;
            } else if (c == 'Y') {
                countY++;
            }
            if (countX == countY) {
                result++;
                countX = 0;
                countY = 0;
            }
        }

        System.out.println(result);
    }
}
