package com.leetcode.huawei.C214考古学家考古问题;

/**
 * 题目描述有一个考古学家发现一个石碑，但是很可惜，发现时其已经断成多段，原地发现n个断口整齐的石碑碎片。为了破解石碑内容，考古学家
 *  希望有程序能帮忙计算复原后的石碑文字组合数，你能帮忙吗？
 *  输入描述
 *  第一行输入n，n表示石碑碎片的个数。
 *  第二行依次输入石碑碎片上的文字内容s，共有n组。
 *  输出描述
 *  输出石碑文字的组合(按照升序排列),行末无多余空格。
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] fragments = new String[n];
        for (int i = 0; i < n; i++) {
            fragments[i] = sc.next();
        }
        Arrays.sort(fragments);
        dfs(fragments, new boolean[n], new StringBuilder(), n);
    }

    private static void dfs(String[] fragments, boolean[] visited, StringBuilder sb, int n) {
        if (sb.length() == n) {
            System.out.println(sb.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] || (i > 0 && fragments[i].equals(fragments[i - 1]) && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            sb.append(fragments[i]);
            dfs(fragments, visited, sb, n);
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
