package com.leetcode.huawei.C40万能单词拼写掌握单词个数;
/*有一个字符串数组 words 和一个字符串 chars。假如可以用 chars 中的字母拼写出 words 中的某个“单词”(字符串), 那么我们就认为你掌握了这个单词。
        words 的字符仅由 a-z 英文小写字母组成，例如 “abc”
        chars 由 a-z 英文小写字母和“?”组成。其中英文 $^{u}?^n$ 表示万能字符，能够在拼写时当作任意一个英文字母。例如：“?”可以当作$^{u}a^{\prime}$等字
        母。
        注意：每次拼写时，chars 中的每个字母和万能字符都只能使用一次。
        输出词汇表 words 中你掌握的所有单词的个数。没有掌握任何单词，则输出0。
        输入描述
        第一行：输入数组 words 的个数，记作N。
        第二行~第N+1行：依次输入数组words的每个字符串元素
        第N+2行：输入字符串chars
        输出描述
        输出一个整数，表示词汇表 words 中你掌握的单词个数*/

// 首先，我们创建一个字符计数器来记录chars中每个字符的数量，
// 然后对于words中的每个单词，我们也创建一个字符计数器来记录每个字符的数量。
// 然后，我们检查单词的字符计数器是否可以由chars的字符计数器覆盖，
// 也就是说，对于单词中的每个字符，其数量都不超过chars中的数量。如果可以，那么我们就认为我们掌握了这个单词
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
        }
        String chars = sc.next();
        int[] charsCount = new int[26];
        for (char c : chars.toCharArray()) {
            if (c != '?') {
                charsCount[c - 'a']++;
            }
        }
        int wildcardCount = (int)chars.chars().filter(c -> c == '?').count();
        int count = 0;
        for (String word : words) {
            int[] wordCount = new int[26];
            for (char c : word.toCharArray()) {
                wordCount[c - 'a']++;
            }
            if (canSpell(wordCount, charsCount, wildcardCount)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean canSpell(int[] wordCount, int[] charsCount, int wildcardCount) {
        for (int i = 0; i < 26; i++) {
            if (wordCount[i] > charsCount[i]) {
                wildcardCount -= wordCount[i] - charsCount[i];
                if (wildcardCount < 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
