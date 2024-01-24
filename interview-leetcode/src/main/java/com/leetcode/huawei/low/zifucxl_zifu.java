package com.leetcode.huawei.low;

import java.util.Scanner;

// 字符串序列判定
public class zifucxl_zifu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        String L = scanner.nextLine();
        // S 和L 的长度暂时不处理

        int indexS = 0;
        int indexL = 0;

        while (indexS < S.length() && indexL < L.length()){
            if(S.charAt(indexS) == L.charAt(indexL)){
                indexS++;
            }
            indexL++;
        }

        if(indexS == S.length()) System.out.println(indexL - 1);
        else System.out.println(-1);
    }
}
