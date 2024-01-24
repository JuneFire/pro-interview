package com.leetcode.huawei.C66密码解密;
/*
题目
给定一段“密文”字符串 s，其中字符都是经过“密码本”映射的，现需要将“密文”解密并输出。映射的规则 (a~i) 分别用 (1~ 9) 表示 (i~z) 分别用 (10* ~26*) 表示约束:映射始终唯一
输入描述
"密文”字符串
输出描述
明文字符串
*/


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 从用户输入接收密文字符串
        String s = scanner.nextLine();

        // 创建一个映射HashMap，用于将加密的'10*'到'26*'映射到对应的字母'j'到'z'
        Map<String, Character> mapping = new HashMap<>();
        for (int i = 10; i <= 26; i++) {
            // 将数字和'*'组合成字符串作为键，将ASCII码转换得到的字母作为值
            mapping.put(i + "*", (char) (96 + i));
        }

        // 更新映射HashMap，将'1'到'9'映射到'a'到'i'
        for (int i = 1; i <= 9; i++) {
            // 将数字转换成字符串作为键，将ASCII码转换得到的字母作为值
            mapping.put(String.valueOf(i), (char) (96 + i));
        }

        // 遍历映射HashMap中的每一对键值对
        for (Map.Entry<String, Character> entry : mapping.entrySet()) {
            // 获取键和值
            String key = entry.getKey();
            Character value = entry.getValue();
            // 使用字符串的replace方法，将密文中的每个加密字符（键）替换为对应的字母（值）
            s = s.replace(key, value.toString());
        }

        // 打印解密后的明文字符串
        System.out.println(s);

        // 关闭扫描器
        scanner.close();
    }
}
