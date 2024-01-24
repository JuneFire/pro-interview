package com.leetcode.huawei.C36小明找位置;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int xiaoMingNumber = Integer.parseInt(sc.nextLine());;

        // 将字符串分割成数组并转换为整数
        String[] numbersStr = line.split(",");
        int[] numbers = new int[numbersStr.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersStr[i].trim());
        }

        // 对数组进行排序
        Arrays.sort(numbers);

        // 使用二分查找找到小明的位置
        int position = Arrays.binarySearch(numbers, xiaoMingNumber);
        // binarySearch()方法的返回值为：
        // 1、如果找到关键字，则返回值为关键字在数组中的位置索引，且索引从0开始
        // 2、如果没有找到关键字，返回值为负的插入点值，所谓插入点值就是第一个比关键字大的元素在数组中的位置索引，
        // 而且这个位置索引从1开始。
        // 如果位置是负数，转换为插入位置
        if (position < 0) {
            position = -position - 1;
        }

        // 输出小明应该排的位置，位置从1开始计数
        System.out.println(position + 1);
    }
}
