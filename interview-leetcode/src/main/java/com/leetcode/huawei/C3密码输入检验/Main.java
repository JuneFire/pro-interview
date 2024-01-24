package com.leetcode.huawei.C3密码输入检验;
/**
 * 题目描述:密码输入检测 (本题分值100)
 * 给定用户密码输入流input，输入流中字符<表示退格，可以清除前一个输入的字符，请你编写程序，输出最终得到的密码字符，并判断密码是否满足如下的密码安全要求。
 * 密码安全要求如下:
 * 1.密码长度>=8.
 * 2.密码至少需要包含1个大写字母
 * 3.密码至少需要包含1个小写字母
 * 4.密码至少需要包含1个数字
 * 5.密码至少需要包含1个字母和数字以外的非空白特殊字符
 * 注意空串退格后仍然为空串，且用户输入的字符串不包合'字符和空白字符
 * 输入描述
 * 用一行字待电表示输入的用户数据，输入的字符由中字符标识浪格，用户输入的字符串不包含空日字符，例如: ABCC89%000<
 * 输出描述
 * 输出经过程序处理后，输出的实际密码字符串，并输出改密码字符串是否满足密码安全要求，两者间由,分隔，例如: ABC89%00,true
 * 示例1
 * 输入
 * ABCeC89%000e
 * 输出
 * ABc89%00true
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String password = processPassword(input);
        boolean isSecure = checkPassword(password);
        System.out.println(password + "," + isSecure);
    }

    private static String processPassword(String input) {
        StringBuilder password = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c == '<') {
                if (password.length() > 0) {
                    password.deleteCharAt(password.length() - 1);
                }
            } else {
                password.append(c);
            }
        }
        return password.toString();
    }

    private static boolean checkPassword(String password) {
        boolean Uppercase = false;
        boolean Lowercase = false;
        boolean Digit = false;
        boolean SpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                Uppercase = true;
            } else if (Character.isLowerCase(c)) {
                Lowercase = true;
            } else if (Character.isDigit(c)) {
                Digit = true;
            } else {
                SpecialChar = true;
            }
        }

        return password.length() >= 8 && Uppercase && Lowercase && Digit && SpecialChar;
    }
}
