package com.leetcode.huawei.C52字符串分割转换;
/*
题目描述给定一个非空宇符串S，其被N个“分隔成N+1的子串，给定正整数K，要求除第- 个子串外，其余的子串每K个字符组成新的子串，并
        用“分隔。
        对于新组成的每一个子串，如果它含有的小写字母比大写字母多，则将这个子串的所有大写字母转换为小写字母；
        反之，如果它含有的大写字母比小写字母多，则将这个子串的所有小写字母转换为大写字母；大小写字母的数量相等时，不做转换。
        输入描述
        输入为两行，第一行为参数K，第二行为字符串S。
        输出描述
        输出转换后的字符串。

        输入：
        3
        12abc-abCABc-4aB@
        输出：
        12abc-abc-ABC-4aB-@

*/

import org.yaml.snakeyaml.nodes.ScalarNode;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        sc.nextLine();
        String S = sc.nextLine();
        String[] parts = S.split("-");
        StringBuilder res = new StringBuilder(parts[0]);
        StringBuilder rest = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            rest.append(parts[i]);
        }
        for (int i = 0; i < rest.length(); i += K) {
            String sub = rest.substring(i, Math.min(i + K, rest.length()));
            int upper = 0, lower = 0;
            for (char c : sub.toCharArray()) {
                if (Character.isUpperCase(c)) upper++;
                else if (Character.isLowerCase(c)) lower++;
            }
            if (upper > lower) sub = sub.toUpperCase();
            else if (lower > upper) sub = sub.toLowerCase();
            res.append("-").append(sub);
        }
        System.out.println(res.toString());
    }
}
