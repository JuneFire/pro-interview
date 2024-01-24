package com.leetcode.huawei.low;


import java.util.*;

// 滑动窗口解决 用连续自然数之和来表达整数
public class zrs_shuzi {

    public static void findContinuousSequences(int target) {
        List<List<Integer>> result = new ArrayList<>();
        int count = 0;
        int left = 1, right = 1;
        int windowSum = 0;

        while (left <= target / 2) {
            if (windowSum < target) {
                windowSum += right;
                right++;
            } else if (windowSum > target) {
                windowSum -= left;
                left++;
            } else {
                List<Integer> sequence = new ArrayList<>();
                for (int i = left; i < right; i++) {
                    sequence.add(i);
                }
                result.add(sequence);
                count++;
                windowSum -= left;
                left++;
            }
        }
        // 使用 lambda 表达式进行排序
        result.sort(Comparator.comparingInt(List::size));

        for (List<Integer> seq : result) {
            Collections.sort(seq);
            System.out.print("Expression: ");
            for (int i = 0; i < seq.size(); i++) {
                System.out.print(seq.get(i));
                if (i < seq.size() - 1) {
                    System.out.print("+");
                }
            }
            System.out.println();
        }

        System.out.println("Result: " + count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int targetInteger = scanner.nextInt();
        findContinuousSequences(targetInteger);
    }
}
