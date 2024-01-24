package com.leetcode.huawei.C43来自异国的客人或幸运数字;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*题目描述有位客人来自异国，在该国使用m进制计数。该客人有个幸运数字n(n<m),每次购物时，其总是喜欢计算本次支付的花费(折算为异国的
        价格后)中存在多少幸运数字。问：当其购买一个在我国价值k的产品时，其中包含多少幸运数字？
        输入描述
        第一行输入为 k, n, m。
        其中：

        · k 表示 该客人购买的物品价值 (以十进制计算的价格) ·n表示 该客人的幸运数字
        ·m 表示 该客人所在国度的采用的进制

        输出描述输出幸运数字的个数，行末无空格。当输入非法内容时，输出0
        用例1
        输入：
        10 2 4
        输出：
        2*/
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
        while (k > 0){
            int num = k % m;
            list.add(num);
            k /= m;
        }
        // 对list降序排序
//        list.sort((o1, o2) -> o2 - o1);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == n){
                count++;
            }
        }
        System.out.println(count);
    }
}
