package com.leetcode.huawei.C215分解连续正整数组合或数的分解;
/*题目描述给定一个正整数 n, 如果能够分解为 m (m>1) 个连续正整数之和，请输出所有分解中，m最小的分解。
        如果给定整数无法分解为连续正整数，则输出字符串"N"。
        输入描述
        输入数据为一整数，范围为 (1,2^n30]
        输出描述
        比如输入为：
        21输出：
        21=10+11*/
import java.util.Scanner;

/**
 * 如果一个数n可以表示为m个连续整数的和，那么这m个连续整数的中间数必须是n/m的整数倍
 * 首先读取输入的整数n，
 * 然后从2开始，依次尝试每个可能的m。
 * 对于每个m，计算m个连续整数的和，然后检查n是否可以被m整除。
 * 如果可以，那么就找到了一个解，并打印出来。如果对于所有的m都找不到解，那么就打印"N"
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        for (long m = 2; m <= n; m++) { // m位分割的个数
            long sum = m * (m + 1) / 2;
            if (sum > n) {
                break;
            }
            if ((n - sum) % m == 0) {
                long start = (n - sum) / m + 1;
                System.out.print(n + "=");
                for (long i = 0; i < m; i++) {
                    if (i != 0) {
                        System.out.print("+");
                    }
                    System.out.print(start + i);
                }
                System.out.println();
                return;
            }
        }
        System.out.println("N");
    }
}
