package com.leetcode.huawei.C53连续字母长度;

/*
题目描述给定一个字符串，只包含大写字母，求在包含同一字母的子串中，长度第 k 长的子串的长度，相同字母只取最长的那个子串。
        输入描述
        第一行有一个子串(1<长度<=100),只包含大写字母。
        第二行为 k的值
        输出描述
        输出连续出现次数第k多的字母的次数。
输入：AAAAHHHBBCDHHHH
        3
输出：2
        同一字母连续出现的最多的是A和H，四次； 第二多的是H，3次，但是H已经存在4个连续的，故不考虑；
        下个最长子串是BB，所以最终答案应该输出2。
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int k = sc.nextInt();
        int[] count = new int[26];
        int maxCount = 0;
        char prev = ' ';
        for (char c : s.toCharArray()) {
            if (c == prev) {
                maxCount++;
            } else {
                maxCount = 1;
                prev = c;
            }
            count[c - 'A'] = Math.max(count[c - 'A'], maxCount);
        }
        List<Integer> list = new ArrayList<>();
        for (int c : count) {
            if (c > 0) {
                list.add(c);
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        if(list.size() < k){
            System.out.println(-1);
        }else {
            System.out.println(list.get(k - 1));
        }
    }
}
