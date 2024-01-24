package com.leetcode.huawei.C29整型数组按个位值排序或最低位排序;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Main {
    public static void main(String[] args) {
        // 创建 Scanner 对象，处理输入
        Scanner scanner = new Scanner(System.in);
        String[] inputNums = scanner.nextLine().split(",");
        List<Integer> numsList = new ArrayList<>();
        for (String inputNum : inputNums) {
            numsList.add(Integer.parseInt(inputNum)); // 将字符串类型的数字转换成整型并添加到列表中
        }
        // 对列表进行排序
        numsList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                return getKey(num1) - getKey(num2); // 根据数字的个位数进行排序
            }
            public Integer getKey(int num) {
                num = num > 0 ? num : -num; // 将数字转换成正数
                return num % 10; // 取余
            }
        });
        // 输出排序后的列表
        for (int i = 0; i < numsList.size(); i++) {
            System.out.print(numsList.get(i));
            if (i != numsList.size() - 1) {
                System.out.print(",");
            }
        }
    }
}

