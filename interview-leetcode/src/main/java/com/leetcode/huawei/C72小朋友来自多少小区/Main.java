package com.leetcode.huawei.C72小朋友来自多少小区;
/*题目描述幼儿园组织活动，老师布置了一个任务：
        每个小朋友去了解与自己同一个小区的小朋友还有几个。
        我们将这些数量汇总到数组 garden 中。
        请根据这些小朋友给出的信息，计算班级小朋友至少来自几个小区？
        输入描述
        输入：
        garden[] ={2,2,3}
         garden 数组长度最大为 999 ·每个小区的小朋友数量最多 1000人。也就是 garden[i] 的范围为[0, 999]
        输出描述输出：7
        用例
        输入 2 2 3
        输出 7
        说明

        第一个小朋友反馈有两个小朋友和自己同一小区，即此小区有3个小朋友。
        第二个小朋友反馈有两个小朋友和自己同一小区，即此小区有3个小朋友。这两个小朋友，可能是同一小区的，且此小区的小朋友只有3个人。
        第三个小区反馈还有3个小朋友与自己同一小区，则这些小朋友只能是另外一个小区的。这个小区有4个小朋友。*/

import java.util.Arrays;
import java.util.Scanner;




import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 创建Scanner对象用于读取标准输入
        Scanner sc = new Scanner(System.in);
        // 读取一行输入并按空格分割
        String[] input = sc.nextLine().split(" ");
        // 创建一个ArrayList用于存储每个小区的孩子数量
        List<Integer> counts = new ArrayList<>();
        // 初始化结果变量为0，用于存储最终的小区数量
        int result = 0;

        // 遍历输入的孩子数量
        for (String child : input) {
            // 将字符串转换为整数表示孩子数量
            int children = Integer.parseInt(child);
            // 确保counts列表的长度足够
            while (children >= counts.size()) {
                // 如果不够，则在counts列表末尾添加0
                counts.add(0);
            }
            // 在对应的索引位置增加孩子数量
            counts.set(children, counts.get(children) + 1);
        }

        // 遍历counts列表
        for (int i = 0; i < counts.size(); i++) {
            // 如果当前索引位置的值大于0
            if (counts.get(i) > 0) {
                // 计算每个小区的实际大小（孩子数量加上自己）
                int districtSize = i + 1;
                // 使用Math.ceil进行向上取整计算至少需要的小区数量，并累加到结果变量中
                result += Math.ceil((double)counts.get(i) / districtSize) * districtSize;
            }
        }

        // 输出最终计算的小区数量
        System.out.println(result);
    }
}

