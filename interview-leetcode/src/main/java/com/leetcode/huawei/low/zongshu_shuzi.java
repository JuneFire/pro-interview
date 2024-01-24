package com.leetcode.huawei.low;


import java.util.*;

public class zongshu_shuzi {
    public static void main(String[] args) {
        // 从标准输入读取整型数组
        Scanner scanner = new Scanner(System.in);
        String[] inputStr = scanner.nextLine().split(" ");
        int[] inputArray = new int[inputStr.length];
        for (int i = 0; i < inputStr.length; i++) {
            inputArray[i] = Integer.parseInt(inputStr[i]);
        }

        // 查找众数
        List<Integer> modes = findMode(inputArray);

        // 计算众数组成的新数组的中位数
        double medianResult = calculateMedian(modes);
        System.out.println(medianResult);
    }

    private static List<Integer> findMode(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int maxCount = 0;

        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
            maxCount = Math.max(maxCount, count);
        }

        List<Integer> modes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == maxCount) {
                modes.add(entry.getKey());
            }
        }

        return modes;
    }

    private static double calculateMedian(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        if (n % 2 == 1) {
            return nums.get(n / 2);
        } else {
            int mid1 = nums.get(n / 2 - 1);
            int mid2 = nums.get(n / 2);
            return (mid1 + mid2) / 2.0;
        }
    }
}
