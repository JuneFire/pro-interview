package com.leetcode.huawei.C2构成指定长度字符串的个数;

/**
 * 题目描述：构成指定长度字符串的个数 (本题分值100) 给定 M (0< M < 30) 个字符 (a-z), 从中取出任意字符 (每个字符只能用一次) 拼接成长度为 N (0< N< 5) 的字符串
 *  要求相同的字符不能相邻，计算出给定的字符列表能拼接出多少种满足条件的字符串，
 *  输入非法或者无法拼接出满足条件的字符串则返回0。
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 创建一个Scanner对象来读取用户的输入
        Scanner scanner = new Scanner(System.in);
        // 读取用户输入的字符串
        String userInput = scanner.nextLine();
        // 将输入的字符串按空格分割为两部分，分别为字符串和长度
        String[] inputParts = userInput.split(" ");
        String inputString = inputParts[0]; // 获取输入的字符串
        int stringLength = Integer.parseInt(inputParts[1]); // 将输入的长度部分转换为整数

        // 调用countDistinctStrings方法计算满足条件的不同字符串的数量
        int distinctStringCount = countDistinctStrings(inputString, stringLength);
        // 输出计算结果
        System.out.println(distinctStringCount);
    }

    // 计算满足条件的不同字符串的数量
    public static int countDistinctStrings(String inputString, int stringLength) {
        // 创建一个HashSet来存储不同的字符串
        HashSet<String> distinctStrings = new HashSet<>();
        // 创建一个boolean数组来标记字符串中的字符是否已经被使用
        boolean[] isUsed = new boolean[inputString.length()];
        // 调用generateDistinctStrings方法生成满足条件的不同字符串
        generateDistinctStrings(inputString, stringLength, "", distinctStrings, isUsed);
        // 打印生成的所有不同的字符串
        // for(String str1 : set){
        // System.out.println(str1);
        // }
        // 返回不同字符串的数量
        return distinctStrings.size();
    }

    // 递归生成满足条件的不同字符串
    public static void generateDistinctStrings(String inputString, int stringLength, String currentString, HashSet<String> distinctStrings, boolean[] isUsed) {
        // 当生成的字符串长度等于指定长度时，将其加入到HashSet中
        if (currentString.length() == stringLength) {
            distinctStrings.add(currentString);
            return;
        }

        // 遍历字符串中的字符
        for (int i = 0; i < inputString.length(); i++) {
            // 判断字符是否已经被使用，或者当前字符与前一个字符相同
            if (isUsed[i] || (currentString.length() > 0 && currentString.charAt(currentString.length() - 1) == inputString.charAt(i))) {
                continue; // 如果字符已被使用或与前一个字符相同，则跳过当前字符
            }
            isUsed[i] = true; // 标记当前字符为已使用
            // 递归调用生成下一个字符
            generateDistinctStrings(inputString, stringLength, currentString + inputString.charAt(i), distinctStrings, isUsed);
            isUsed[i] = false; // 取消标记当前字符的使用状态，以便下一次遍历
        }
    }
}




