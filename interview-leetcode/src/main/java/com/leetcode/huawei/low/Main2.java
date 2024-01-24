package com.leetcode.huawei.low;

import java.util.*;

//输入描述
//        一个目标整数T(1<=T<=1000)
//        输出描述
//        该整数的所有表达式和表达式的个数。如果有多种表达式，输出要求为:自然数个数最少的表达式优先输出
//        每个表达式中按自然数递增的顺序输出，具体的格式参见样例。
//        在每个测试数据结束时，输出一行"Result:X”，其中X是最终的表达式个数。
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        // 输出目标整数T
        System.out.println(target + "=" + target);
        List<String> expre = new ArrayList<>();
        for (int i = 1; i < target; i++) {
            int sum = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = i; sum < target; j++) {
                sum += j;
                sb.append(j).append("+");
                if(sum == target){
                    expre.add(target + "=" +sb.substring(0, sb.length() - 1));
                    break;
                }
            }
        }

        Collections.sort(expre, Comparator.comparingInt(String::length));
        for (String s : expre) {
            System.out.println(s);
        }
        System.out.println("Result:" + (expre.size() ));
    }


}
