package com.leetcode.huawei.C11环中最长子串字符成环找偶数O;

public class Main {
    public static void main(String[] args) {
        // Input string
        String s = "abcdooo";

        // Count the number of 'o' characters
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'o') {
                count++;
            }
        }

        // Determine the maximum length of the substring
        int maxLen;
        if (count % 2 == 0) {
            // If the number of 'o' characters is even, the maximum length is the length of the string
            maxLen = s.length();
        } else {
            // If the number of 'o' characters is odd, the maximum length is the length of the string minus 1
            maxLen = s.length() - 1;
        }

        System.out.println(maxLen);
    }
}
