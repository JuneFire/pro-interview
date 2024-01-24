package com.leetcode.huawei.C48最少停车数或停车场车辆统计;


/*题目描述
        特定大小的停车场，数组cars[]表示，其中1表示有车，0表示没车。车辆大小不一，小车占一个车位(长度1),货车占两个车位(长度
        2),卡车占三个车位(长度3) 。
        统计停车场最少可以停多少辆车，返回具体的数目。
        输入描述
        整型字符串数组cars[],其中1表示有车，0表示没车，数组长度小于1000。
        输出描述
        整型数字字符串，表示最少停车数目。*/

import javax.print.DocFlavor;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] cars = sc.nextLine().split(",");
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,0);
        map.put(2,0);
        map.put(3,0);
        int a = 0, b = 0, c =0;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].equals("1")) {
                count++;
            } else {
                map.put(count,map.getOrDefault(count, 0) + 1);
                count = 0;
            }
        }
        map.put(count,map.getOrDefault(count, 0) + 1);
        a = map.get(1);
        b = map.get(2);
        c = map.get(3);
        System.out.println(a + " " + b + " " + c);
    }
}
