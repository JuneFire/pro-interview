package com.leetcode.huawei.C54火星文计算;
/*
题目描述已知火星人使用的运算符为#、$, 其与地球人的等价公式如下：
        x#y=2^{\star}x+3^{\star}y+4$
        x$y=3^{*}x+y+2$ 1.其中x、y是无符号整数

        2.地球人公式按C语言规则计算
        3.火星人公式中，S的优先级高于#, 相同的运算符，按从左到右的顺序计算
        现有一段火星人的字符串报文，请你来翻译并计算结果。
        输入描述
        火星人字符串表达式 (结尾不带回车换行)
        输入的字符串说明：字符串为仅由无符号整数和操作符 (#、S) 组成的计算表达式。
        例如：123#4$5#67$78。
        1.用例保证字符串中，操作数与操作符之间没有任何分隔符。
        2.用例保证操作数取值范围为32位无符号整数。
        3.保证输入以及计算结果不会出现整型溢出。
        4. 保证输入的字符串为合法的求值报文，例如：123#4$5#67$78
        5. 保证不会出现非法的求值报文，例如类似这样字符串：

        #495 11缺少操作数4S5#111缺少操作数4#35 11缺少操作数4 95 11有空格

        $3+4-5^*6/7$ //有其它操作符
        12345678987654321\$54321 //32位整数计算溢出
        输出描述
        根据输入的火星人字符串输出计算结果 (结尾不带回车换行) 。
*/

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String str = "7#6$5#12";
        System.out.println(operation(str));
    }

    public static long operation(String str) {
        Stack<Long> stack = new Stack<>();
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                int start = i;
                while (i < str.length() && Character.isDigit(str.charAt(i))) {
                    i++;
                }
                long num = Long.parseLong(str.substring(start, i));
                stack.push(num);
            } else {
                if (c == '$') {
                    long x = stack.pop();
                    i++;
                    int start = i;
                    while (i < str.length() && Character.isDigit(str.charAt(i))) {
                        i++;
                    }
                    long y = Long.parseLong(str.substring(start, i));
                    stack.push(x * 3 + y + 2);
                } else if (c == '#') {
                    i++;
                }
            }
        }
        Stack<Long> stack2 = new Stack<>();
        while (!stack.empty()){
            stack2.push(stack.pop());
        }
        long res = stack2.pop();
        while (!stack2.empty()){
            res = 2 * res + 3 * stack2.pop()  + 4;
        }
        return res;
    }

}
