package com.leetcode.offer;

/**
 * @author zkCheng
 * @date 2023/4/10 11:22
 */
public class Offer1630 {


    /**
     * 剑指 Offer 16. 数值的整数次方
     * https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/description/
     * 思路：采用二进制原理，对n进行叠加
     */
    public double myPow(double x, int n) {

        double res = 1;
        long y = n;// 不能用 int。
        if (n < 0) {
            y = -y;
            x = 1 / x;
        }
        while (y > 0) {
            if (y % 2 == 1) {
                res = res * x;
            }

            x = x * x;
            y = y / 2;
        }
        return res;
    }

    /**
     * 剑指 Offer 19. 正则表达式匹配
     * https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/description/
     * TODO
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return true;
        }
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        // 初始化
        dp[0][0] = true;
        for (int j = 2; j <= m; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 当j不为 *
                if (p.charAt(j - 1) != '*') {
                    // 如果不匹配
                    if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    // 第j-1个字符不匹配
                    if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[n][m];
    }

    /**
     * 剑指 Offer 20. 表示数值的字符串
     * https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/description/
     */
    public boolean isNumber(String s) {
        // 判断特例
        if (s == null || s.length() <= 0) {
            return false;
        }
        char[] chars = s.trim().toCharArray();
        if (chars.length <= 0) {
            return false;
        }

        boolean is_num = false;
        boolean is_dot = false;
        boolean is_e_or_E = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                is_num = true;
            } else if (chars[i] == '.') {  // 遇见小数点，前面：不能有重复的小数点，也不能出现 e/E
                if (is_dot || is_e_or_E) {
                    return false;
                }
                is_dot = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {  // 遇见e/E
                //前面必须要有一个数字 || 前面不能出现重复的 e/E
                if (is_e_or_E || !is_num) {
                    return false;
                }
                is_e_or_E = true;
                is_num = false; // 11E+  11E
            } else if (chars[i] == '+' || chars[i] == '-') { // +、-符号必须出现在首位，或则e/E的后一位
                if (i != 0 && chars[i - 1] != 'e' && chars[i - 1] != 'E') {
                    return false;
                }
            } else {
                return false;
            }

        }
        return is_num;
    }

    /**
     * 剑指 Offer 26. 树的子结构
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 双递归
    public boolean isSubStructure(TreeNode A, TreeNode B) {

        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    // 递归判断该子树节点是否匹配
    public boolean recur(TreeNode A, TreeNode B) {
        if (B == null) return true;  // 完全匹配，B给走完了
        if (A == null || A.val != B.val) return false; //A走完了还没配上
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}
