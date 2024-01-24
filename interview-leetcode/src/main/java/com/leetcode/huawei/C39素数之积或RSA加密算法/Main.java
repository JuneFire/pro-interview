package com.leetcode.huawei.C39素数之积或RSA加密算法;
/*

   给定一个32位正整数，请
        对其进行因数分解，找出是哪两个素数的乘积。
        输入描述
        一个正整数num，$0< $num$< = 2147483647$
        输出描述
        如果成功找到，以单个空格分割，从小到大输出两个素数，分解失败，请输出-1,-1
*/

import java.util.Scanner;

// 这个问题可以通过遍历2到sqrt(num)的所有整数来解决，检查每个整数是否是num的因数。如果找到了一个因数，并且它和num除以它的结果都是素数，那么就找到了答案
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0 && isPrime(i) && isPrime(num / i)) {
                System.out.println(i + " " + num / i);
                return;
            }
        }
        System.out.println("-1 -1");
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
