package com.leetcode.huawei.C226数字游戏;
/*题目描述小扇和小船今天又玩起来了数字游戏，小船给小扇一个正整数n(1≤n≤1e9),小扇需要找到一个比n大的数字 m, 使得 m 和 n 对应
        的二进制中 1 的个数要相同，如：
        · 4对应二进制100
        · 8对应二进制1000 ·其中1的个数都为1个

        现在求 m 的最小值。
        输入描述
        输入一个正整数 n $( 1\leq n\leq 1$e$9) $
        输出描述
        输出一个正整数 m*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(minM(n));
    }
//找到n的二进制表示中从右向左的第一个"01"，然后将其变为"10"，这样可以保证1的个数不变，同时得到的数是大于n的最小数。
    public static int minM(int n) {
        int m = n;
        for (int i = 0; i < 31; i++) {
            if (((m >> i) & 1) == 1 && ((m >> (i + 1)) & 1) == 0) {
                m |= (1 << (i + 1));
                m &= ~(1 << i);
                break;
            }
        }
        return m;
    }
}


/*

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = findNextNumberWithSameNumberOfOnes(n);
        System.out.println(m);
        scanner.close();
    }

    private static int findNextNumberWithSameNumberOfOnes(int n) {
        // c0 表示在找到的 "01" 模式中 0 的个数
        // c1 表示在找到的 "01" 模式中 1 的个数
        int c0 = 0, c1 = 0;
        int temp = n;

        // 统计 "01" 模式中 0 的个数
        while (((temp & 1) == 0) && (temp != 0)) {
            c0++;
            temp >>= 1;
        }

        // 统计 "01" 模式中 1 的个数
        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }

        // 如果 n 是形如 "111...11000...0" 的数，则没有 "01" 模式
        if (c0 + c1 == 31 || c0 + c1 == 0) {
            return -1;
        }

        // p 是我们要翻转的 "01" 模式的位置
        int p = c0 + c1;

        // 翻转 "01" 为 "10"
        // 第一步：将 p 位置为 1（即将 "01" 的 "0" 翻转为 "1"）
        n |= (1 << p);

        // 第二步：清除 p 位右边的所有位（即将 "01" 后面的所有位清零）
        // 创建一个掩码，它在 p 位之前都是 1，然后取反，得到 p 位及其右边都是 0 的掩码
        int mask = ~((1 << p) - 1);
        n &= mask;

        // 第三步：在 p 位右边插入 (c1-1) 个 1（即将 "01" 前面的 "1" 移动到 p 位右边）
        // 创建一个序列，其中包含 (c1-1) 个 1，然后将这个序列放在 p 位的右边
        int ones = (1 << (c1 - 1)) - 1;
        n |= ones;

        return n;
    }
}
*/
