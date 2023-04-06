package main.java.leetcode.L300;

import java.util.Arrays;

/**
 * @author zkCheng
 * @date 2022/11/17 16:40
 */
public class findTheLongestSubstring {



    // 寻找每个元音包含偶数次的最长子字符串
    public static int findVowelTheLongestSubstring(String s) {
        int[] pos = new int[1 << 5];  // 准备长度为2^31的数组
        int status = 0;  // 为0时是偶数，其它则为奇数
        Arrays.fill(pos, -1);
        int ans = 0;
        pos[0] = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                status ^= 1;    // 奇偶奇偶的重复变化
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            // 计算最大长度字串
            if (pos[status] >= 0) {
                // 若 state 的值为0，说明当前 0 到 i 内出现的元音均为偶数次。若不为0，则存在某个或者某几个元音为奇数次。
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;  //
            }
        }
        return ans;
    }

    public static void main(String[] args){
        System.out.println(findVowelTheLongestSubstring("leoeo")); //leotecodeisgreat
    }
}
