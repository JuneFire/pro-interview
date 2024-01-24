package com.leetcode.huawei.C33字符串变换最小字符串;
/*题目描述给定一个字符串s，最多只能进行一次变换，返回变换后能得到的最小字符串 (按照字典序进行比较) 。
        变换规则：交换字符串中任意两个不同位置的字符。
        输入描述
        一串小写字母组成的字符串S
        输出描述
        按照要求进行变换得到的最小字符串。*/

import java.util.Arrays;
import java.util.Scanner;
/*

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // 对字符串进行排序
        char[] sortedArr = s.toCharArray();
        Arrays.sort(sortedArr);

        // 如果排序后的字符串与原字符串相同，则说明已经是最小字符串，直接输出
        if (new String(sortedArr).equals(s)) {
            System.out.println(s);
            return;
        }

        // 遍历原字符串
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            // 如果当前字符与排序后的字符不相同，则进行交换
            if (s.charAt(i) != sortedArr[i]) {
                char tmp = sb.charAt(i);
                int swapIndex = -1;
                // 找到排序后的字符在原字符串中的位置
                for (int j = i + 1; j < s.length(); j++) {
                    if (sb.charAt(j) == sortedArr[i]) {
                        swapIndex = j;
                    }
                }
                // 将原字符与排序后的字符交换
                sb.setCharAt(i, sortedArr[i]);
                sb.setCharAt(swapIndex, tmp);
                break;
            }
        }

        // 输出最小字符串
        System.out.println(sb.toString());
    }
}
*/

public class Main {
    //这个问题可以通过找到字符串中的最小字符，然后将它与字符串的第一个字符交换来解决。如果最小字符有多个，我们应该选择最后一个最小字符进行交换，这样可以保证交换后的字符串是最小的
    public static String smallestStringAfterSwap(String s) {
        char[] chars = s.toCharArray();

        int minIndex = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] <= chars[minIndex]) {
                minIndex = i;
            }
        }

        if (minIndex != 0) {
            char temp = chars[0];
            chars[0] = chars[minIndex];
            chars[minIndex] = temp;
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        String s = "bcdefaaaaa";
        System.out.println(smallestStringAfterSwap(s));
    }
}
