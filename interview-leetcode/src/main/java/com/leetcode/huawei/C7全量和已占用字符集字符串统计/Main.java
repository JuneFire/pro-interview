package com.leetcode.huawei.C7全量和已占用字符集字符串统计;
import com.leetcode.Mind.Sort;

import java.util.HashMap;
// 输入描述
// 1.输入一个字符串 一定包含@,@前为全量字符集@后的为已占用字符集
//  2.已占用字符集中的字符一定是全量字符集中的字符
//  3.字符集中的字符跟字符之间使用英文逗号隔开
//  4.每个字符都表示为字符+数字的形式用英文冒号分隔，比如a：1标识一个a字符
//  5.字符只考虑英文字母，区分大小写
//  6.数字只考虑正整型 不超过100
//  7.如果一个字符都没被占用 @标识仍存在，例如 a:3,b:5,c2@

// 输出描述·输出可用字符集
//  ·不同的输出字符集之间用回车换行
//  ·注意 输出的字符顺序要眼输入的一致，如下面用例不能输出b:3,a:2,c:2
//  · 如果某个字符已全部占用 则不需要再输出

public class Main {
    public static void main(String[] args) {
        String input = "a:3,b:5,c:2@a:1,b:2";
        String[] sets = input.split("@");
        if(sets.length != 2) {
            System.out.println(input);
            return;
        }
        String fullCharacterSet = sets[0];
        String occupiedCharacterSet = sets[1];

        String[] fullCharacters = fullCharacterSet.split(",");
        String[] occupiedCharacters = occupiedCharacterSet.split(",");

        HashMap<Character, Integer> characterCount = new HashMap<>();

        for (String character : fullCharacters) {
            String[] parts = character.split(":");
            char c = parts[0].charAt(0);
            int count = Integer.parseInt(parts[1]);
            characterCount.put(c, count);
        }

        for (String character : occupiedCharacters) {
            String[] parts = character.split(":");
            char c = parts[0].charAt(0);
            int count = Integer.parseInt(parts[1]);
            characterCount.put(c, characterCount.get(c) - count);
        }

        for (char c : characterCount.keySet()) {
            int count = characterCount.get(c);
            if (count > 0) {
                System.out.println(c + ":" + count);
            }
        }
    }
}
