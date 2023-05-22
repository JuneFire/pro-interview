package com.leetcode.L300.贪心;

import java.util.Arrays;

/**
 * @author zkCheng
 * @date 2022/11/18 11:05
 */
public class Solution {

    // 455. 分发饼干  https://leetcode.cn/problems/assign-cookies/description/
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int cnt = 0;
        int temp = 0;
        for (int k : g) {
            int j = temp;
            while (j <= s.length - 1) {
                if (k <= s[j]) {
                    cnt++;
                    temp = ++j;
                    break;
                }
                j++;
            }
        }
        return cnt;
    }

    // 思路1：优先考虑饼干，小饼干先喂饱小胃口
    public static int findContentChildren1(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int cnt = 0;
        int start = 0;
        for (int i = 0; i < s.length && start < g.length; i++) {
            if (s[i] >= g[start]) {
                start++;
                cnt++;
            }
        }
        return cnt;
    }

    // 思路2：优先考虑胃口，先喂饱大胃口
    public static int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int start = s.length - 1;
        // 遍历胃口
        for (int index = g.length - 1; index >= 0; index--) {
            if (start >= 0 && g[index] <= s[start]) {
                start--;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        findContentChildren(new int[]{7, 8, 9, 10}, new int[]{5, 6, 7, 8});
        System.out.println(findContentChildren2(new int[]{1, 2, 3}, new int[]{3}));
    }
}
