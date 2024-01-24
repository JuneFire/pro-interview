package com.leetcode.huawei.C1山脉的个数;

/**
 * 给定 M (0< M< 30) 个字符 (a-z), 从中取出任意字符 (每个字符只能用一次) 拼接成长度为 N ($0<\mathbb{N}\leq5)$ 的字符串， 要求相同的字符不能相邻，计算出给定的字符列表能拼接出多少种满足条件的字符串，
 *  输入非法或者无法拼接出满足条件的字符串则返回0。
 */
public class Main {

    public static int countPeaks(int[] hillMap) {
        int count = 0;

        for (int i = 0; i < hillMap.length; i++) {
            if ((i == 0 || hillMap[i] > hillMap[i - 1]) &&
                    (i == hillMap.length - 1 || hillMap[i] > hillMap[i + 1])) {
                count++;
            }
        }

        return count;
    }

}
