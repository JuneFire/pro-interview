package com.leetcode.huawei.C63求字符串中所有整数的最小和;
/*题目描述输入字符串s，输出s中包含所有整数的最小和。
        说明：
        字符串s，只包含 a-z A-Z\pm ;
        合法的整数包括

        ·正整数一个或者多个0.9组成，如023002 102 ·负整数 负号 (-) 开头，数字部分由一个或者多个0-9组成，如 -0 -012 -23 -00023

        输入描述包含数字的字符串
        输出描述
        所有整数的最小和
        用例1
        输入
        bb12-34aa
        输出
        -31
        */
import java.util.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(s);
        int sum = 0;
        while (m.find()) {
            String str = m.group();
            int res = Integer.parseInt(str);
            if(res > 0) {
                for (int i = 0; i < str.length(); i++) {
                    sum += Integer.parseInt(str.charAt(i) + "");
                }
            }else {
                sum += res;
            }
        }
        System.out.println(sum);
    }
}
