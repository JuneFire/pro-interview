package com.leetcode.huawei.low;

import java.util.*;

public class zcyyzc_zifu {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        // 输入瑕疵度
//        int flaw = scanner.nextInt();
//        scanner.nextLine(); // 读取换行符
//        // 输入字符串
//        String s = scanner.nextLine();

        int flaw = 0;
        String s = "asdbuiodevauufgh";
        // 定义元音字母集合
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        // 记录字符串中所有元音字母的下标
        List<Integer> vowelIdxs = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                vowelIdxs.add(i);
            }
        }
        // 0 4 5 6 8 10 11 12
        // 初始化双指针
        int left = 0, right = 0;
        // 记录所有满足瑕疵度的元音子串的长度
        List<Integer> lengths = new ArrayList<>();
        while (right < vowelIdxs.size()) {
            // 计算当前子串的瑕疵度 (当前元音子串的长度减去元音字母个数的结果)
            // (a - b + 1) - (c - d + 1)
            int lengthDiff = vowelIdxs.get(right) - vowelIdxs.get(left) - (right - left);
            if (lengthDiff > flaw) {
                // 如果瑕疵度超过了预期，左指针右移
                left++;
            } else {
                // 如果瑕疵度不超过预期，记录子串长度
                if (lengthDiff == flaw) {
                    lengths.add(vowelIdxs.get(right) - vowelIdxs.get(left) + 1);
                }
                // 右指针右移
                right++;
            }
        }
        // 如果没有满足瑕疵度的元音子串，输出 0
        if (lengths.isEmpty()) {
            System.out.println(0);
            return;
        }
        // 输出最长的元音子串的长度
        Collections.sort(lengths, Collections.reverseOrder());
        System.out.println(lengths.get(0));
    }
}
