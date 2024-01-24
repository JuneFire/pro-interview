package com.leetcode.huawei.C4字符串序列判定;
/**
 * 题目描述: 字符串序列判定/最后一个有效字符 (本题分值100)
 * 输入两人字符串S和L，都只包含英文小写字母。S长度<=100，L长度<=500.000。判定S是否是L的有效子串.
 * 判定规则:
 * S中的每个字符在L中都能找到 (可以不连续)
 * 且S在L中字符的前后顺序与S中顺序要保持一致。
 * (例如，S=”ace"是L="abcde"的一个子序列且有效字符是a、c、e，而“aec"不是有效子序列，且有效字符只有a、e)
 * 输入描述
 * 输入两个字符串S和L，都只包含英文小写字母。S长度<=100，L长度<=500.000.先输入S，再输入L，每个字符串占一行
 * 输出描述
 * (首位从0开始计算，无有效字符返回-1)输出S串最后一个有效字符在L中的位置。
 */

import java.util.*;

public class Main {
    /**
     * 主函数，用于接收用户输入的字符串并调用findLastValidCharacter方法进行处理
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        String L = scanner.nextLine();
        int result = findLastValidCharacter(S, L);
        System.out.println(result);
    }

    /**
     * 查找最后一个有效字符的索引
     * @param S 字符串S
     * @param L 字符串L
     * @return 最后一个有效字符的索引，如果没有有效字符则返回-1
     */
    public static int findLastValidCharacter(String S, String L) {
        int sIndex = 0;
        int lIndex = 0;
        int lastValidIndex = -1;

        while (sIndex < S.length() && lIndex < L.length()) {
            if (S.charAt(sIndex) == L.charAt(lIndex)) {
                lastValidIndex = lIndex;
                sIndex++;
            }
            lIndex++;
        }

        return lastValidIndex;
    }
}
