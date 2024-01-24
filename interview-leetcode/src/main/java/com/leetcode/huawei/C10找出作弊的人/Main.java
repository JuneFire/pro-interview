package com.leetcode.huawei.C10找出作弊的人;

// 公司组织了一次考试，现在考试结果出来了，想看一下有没人存在作弊行为，但是员工太多了，需要先对员工进行一次过滤，再进一步确定是否存在作弊行为。
//  过滤的规则为：找到分差最小的员工ID对(p1,p2)列表，要求p1<p2
//  员工个数取值范国：0<n<100000
//  员工ID为整数，取值范围：0<=n<=100000
//  考试成绩为整数，取值范围：0<=score<=300
//  输入描述
//  员工的ID及考试分数
//  输出描述
//  分差最小的员工ID对(p1,p2)列表，要求p1<p2。每一行代表一个集合，每个集合内的员工ID按顺序排列，多行结果也以员工对中p1值大小升序
//  排列(如果p1相同则p2升序)。
// 输入：
// 5
// 1 90
// 2 91
// 3 95
// 4 96
// 5 100
// 输出：
// 1 2
// 3 4

import java.util.*;

class Employee {
    int id;
    int score;

    Employee(int id, int score) {
        this.id = id;
        this.score = score;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            int score = sc.nextInt();
            employees.add(new Employee(id, score));
        }

        // 按分数排序，如果分数相同则按ID排序
        Collections.sort(employees, (a, b) -> a.score == b.score ? a.id - b.id : a.score - b.score);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < employees.size(); i++) {
            minDiff = Math.min(minDiff, employees.get(i).score - employees.get(i - 1).score);
        }
        List<int[]> result = new ArrayList<>();
        for (int i = 1; i < employees.size(); i++) {
            if (employees.get(i).score - employees.get(i - 1).score == minDiff) {
//                System.out.println(employees.get(i - 1).id + " " + employees.get(i).id);
                result.add(new int[]{employees.get(i - 1).id, employees.get(i).id});
            }
        }
        result.sort(Comparator.comparingInt(a -> a[0])); // 按照第一个元素升序排序
        result.forEach(a -> System.out.println(a[0] + " " + a[1]));
    }
}
