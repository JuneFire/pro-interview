package com.leetcode.huawei.low;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class zifu_shizi {
    int count = 0;
    static List<Integer> list = new ArrayList<>();

    public static int findnum(char[] ch){
        int sum = 0;
        for (int i = 0; i < ch.length; i++) {
            if(Character.isDigit(ch[i])){
                sum++;
                list.add(sum);
            }
        }

        int max = Collections.max(list);
        if(max == ch.length || max == 0){
            return -1;
        }
        return max+1;
    }

    public static void main(String[] args){
        String str = "aBcD1234567890EFGHijklmnopqr12313stuvwxyZ";
        System.out.println(findnum(str.toCharArray()));
    }
}
