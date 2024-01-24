package com.leetcode.huawei.C24寻找身高相近的小朋友;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // 创建Scanner对象用于读取输入
        Scanner sc = new Scanner(System.in);
        // 读取小明的身高h和新班级其他小朋友个数n
        int h = sc.nextInt();
        int n = sc.nextInt();
        // 创建一个ArrayList用于存储其他小朋友的身高
        ArrayList<Integer> heights = new ArrayList<Integer>();
        // 读取其他小朋友的身高并添加到ArrayList中
        for (int i = 0; i < n; i++) {
            int height = sc.nextInt();
            heights.add(height);
        }
        // 对ArrayList中的身高进行排序
        Collections.sort(heights, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        int diff_a = Math.abs(o1 - h);
                        int diff_b = Math.abs(o2 - h);
                        // 如果两个小朋友和小明身高差一样，则个子较小的小朋友排在前面
                        if(diff_a == diff_b){
                            return o1 - o2;
                        }
                        //否则按绝对值升序排列
                        return diff_a - diff_b;
                    }
                });


//        // 对ArrayList中的身高进行排序
//        Collections.sort(heights, new Comparator<Integer>() {
//            // 自定义比较器，根据与小明身高差的绝对值进行排序
//            public int compare(Integer a, Integer b) {
//                int diff_a = Math.abs(a - h);
//                int diff_b = Math.abs(b - h);
//                // 如果两个小朋友和小明身高差一样，则个子较小的小朋友排在前面
//                if (diff_a == diff_b) {
//                    return a - b;
//                }
//                // 否则，根据与小明身高差的绝对值进行排序
//                return diff_a - diff_b;
//            }
//        });
                // 输出排序后的结果
        for (int i = 0; i < n; i++) {
            System.out.print(heights.get(i) + " ");
        }
        System.out.println();
    }
}
