package com.leetcode.huawei.C5用连续自然数之和来表达整数;

import java.util.*;
import java.util.*;

/*
一个整数可以由连续的自然数之和来表示。给定一个整数，计算该整数有几种连续自然数之和的表达式，且打印出每种表达式
 输入描述
 一个目标整数T $(1<=T<=1000)$
 输出描述
 该整数的所有表达式和表达式的个数。如果有多种表达式，输出要求为：
 自然数个数最少的表达式优先输出
 每个表达式中按自然数递增的顺序输出，具体的格式参见样例。
 在每个测试数据结束时，输出一行"Result:X”,其中X是最终的表达式个数。

*/


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        List<List<Integer>> expressions = findExpressions(T);
        System.out.println(T + "=" + T);
        printExpressions(expressions,T);
        System.out.println("Result: " + (expressions.size() + 1));
    }

    private static List<List<Integer>> findExpressions(int T) {
        List<List<Integer>> expressions = new ArrayList<>();
        int start = 1;
        int end = 2;
        int sum = start + end;

        while (start < T) {
            if (sum == T) {
                List<Integer> expression = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    expression.add(i);
                }
                expressions.add(expression);
                sum -= start;
                start++;
            } else if (sum < T) {
                end++;
                sum += end;
            } else {
                sum -= start;
                start++;
            }
        }
        Collections.sort(expressions,Comparator.comparingInt(List::size));
        return expressions;
    }

    private static void printExpressions(List<List<Integer>> expressions, int T) {
        for (List<Integer> expression : expressions) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < expression.size(); i++) {
                if(i == 0){
                    sb.append(T).append("=").append(expression.get(i));;
                }else {
                    sb.append(expression.get(i));
                }
                if (i < expression.size() - 1) {
                    sb.append("+");
                }
            }
            System.out.println(sb.toString());
        }
    }
}
