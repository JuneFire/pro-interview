package com.leetcode.huawei.C28字符串摘要;

import static java.lang.Character.isLetter;

/*题目描述
        给定一个字符串的摘要算法，请输出给定字符串的摘要值
        1.去除字符串中非字母的符号
        2.如果出现连续字符(不区分大小写)，则输出: 该字符(小写) + 连续出现的次数
        3.如果是非连续的字符(不区分大小写)，则输出: 该字符(小写)+ 该字母之后字符串中出现的该字符的次数
        4.对按照以上方式表示后的字符事进行排序:字母和紧随的数字作为一组进行排序，数字大的在前，数字相同的，则按字母进行排序字母小的在前。
        输入描述
        行字符串，长度为[1,200]
        输出描述
        摘要字符串*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
public class Main.java {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.toLowerCase(); // 将输入的字符串转换为小写

        // 统计每个字符出现的次数
        int[] charCount = new int[128]; // ASCII 码表中共有 128 个字符
        StringBuilder sb = new StringBuilder(); // 用于存储去除非字母的符号后的字符串
        for (char c : input.toCharArray()) { // 遍历字符串的每个字符
            if (c >= 'a' && c <= 'z') { // 如果该字符是字母
                charCount[c]++; // 该字符出现次数加 1
                sb.append(c); // 将该字符添加到 sb 中
            }
        }

        // 在每个字符后面加上其出现的次数或者连续出现的次数
        input = sb + " "; // 在字符串末尾加上一个空格，方便统计最后一个字符的出现次数
        ArrayList<String> ans = new ArrayList<>(); // 用于存储每个字符的摘要值
        char pre = input.charAt(0); // pre 表示当前正在处理的字符的前一个字符
        int repeat = 1; // repeat 表示当前正在处理的字符的连续出现次数
        charCount[pre]--; // 将 pre 出现的次数减 1
        for (int i = 1; i < input.length(); i++) { // 遍历字符串的每个字符
            char cur = input.charAt(i); // cur 表示当前正在处理的字符
            charCount[cur]--; // 将 cur 出现的次数减 1
            if (cur == pre) { // 如果 cur 和 pre 相等，表示出现了连续字符
                repeat++; // 连续出现次数加 1
            } else { // 如果 cur 和 pre 不相等，表示出现了非连续字符
                ans.add(pre + "" + (repeat > 1 ? repeat : charCount[pre])); // 将 pre 的摘要值添加到 ans 中
                pre = cur; // 更新 pre 为当前字符
                repeat = 1; // 重置连续出现次数为 1
            }
        }

        // 排序
        Object[] ansArray = ans.toArray(); // 将 ans 转换为数组
        Arrays.sort(ansArray, (a, b) -> { // 根据题目要求对 ansArray 进行排序
            String s1 = (String) a;
            String s2 = (String) b;
            if (s1.charAt(s1.length() - 1) != s2.charAt(s2.length() - 1)) { // 如果最后一个字符不相等，按照数字大小排序
                return s2.charAt(s2.length() - 1) - s1.charAt(s1.length() - 1);
            } else { // 如果最后一个字符相等，按照字母顺序排序
                return s1.charAt(0) - s2.charAt(0);
            }
        });

        // 输出结果
        StringBuilder res = new StringBuilder(); // 用于存储最终的摘要值
        for (Object an : ansArray) { // 遍历 ansArray
            res.append(an); // 将每个元素添加到 res 中
        }
        System.out.println(res.toString()); // 输出最终的摘要值
    }
}*/


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();

        int[] charCount = new int[128];
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                charCount[c]++;  // 记录每个字符总出现次数
                sb.append(c);
            }
        }

        input = sb + " ";
        ArrayList<String> ans = new ArrayList<>();
        char pre = input.charAt(0);
        int repeat = 1;
        charCount[pre]--;
        for (int i = 1; i < input.length(); i++) {
            char cur = input.charAt(i);
            charCount[cur]--;
            if (cur == pre) {
                repeat++;
            } else {  // 如果 cur 和 pre 不相等，表示出现了非连续字符
                ans.add(pre + "" + (repeat > 1 ? repeat : charCount[pre]));
                pre = cur;
                repeat = 1;
            }
        }

        //字符事进行排序:字母和紧随的数字作为一组进行排序，数字大的在前，数字相同的，则按字母进行排序字母小的在前。

        ans.sort((a, b) -> {
            int numA = Integer.parseInt(a.substring(1));
            int numB = Integer.parseInt(b.substring(1));
//            int numA = Character.getNumericValue(a.charAt(a.length() - 1));
//            int numB = Character.getNumericValue(b.charAt(b.length() - 1));
            if (numA != numB) {
                return numB - numA;
            } else {
                return a.charAt(0) - b.charAt(0);
            }
        });

        StringBuilder res = new StringBuilder();
        for (String an : ans) {
            res.append(an);
        }
        System.out.println(res.toString());
    }
}
