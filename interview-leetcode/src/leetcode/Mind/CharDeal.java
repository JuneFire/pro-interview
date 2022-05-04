package leetcode.Mind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zkcheng
 * @Date: 2021/07/26/12:41
 * @Description: 字符串处理
 */
public class CharDeal {

    //最⻓公共前缀
    public static String replaceSpace(String[] strs){

        if (!chechStrs(strs)) {
            return "";
        }

        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < strs[0].length()){
            char temp = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if(strs[j].charAt(i) == temp){
                    continue;
                } else {
                    return res.toString();
                }
            }
            i++;
            res.append(temp);
        }
        return res.toString();
    }

    private static boolean chechStrs(String[] strs) {
        boolean flag = false;
        if(strs != null){
            for (int i = 0; i < strs.length; i++) {
                if(strs[i] != null && strs[i].length() != 0){
                    flag = true;
                }else {
                    flag = false;
                }
            }
        }
        return flag;
    }

    //给定一个字符串，返回该字符串可以组成的最大回文串


    // 最长回文子序列
    public int longestPalindRomeSubeq(String s){
        if(s == null || s.length() == 0) return 0;

        int n = s.length();
        char[] S = s.toCharArray();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if(S[i] == S[j]){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                  dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    //字符串转数字
    public static int StrToInt(String str) {
        if (str.length() == 0)
        return 0;
        char[] chars = str.toCharArray();

        int flag = 0;
        if (chars[0] == '+')
            flag = 1;
        else if (chars[0] == '-')
            flag = 2;
        int start = flag > 0 ? 1 : 0;
        int res = 0;
        for (int i = start; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                int temp = chars[i] - '0';
                res = res * 10 + temp;
            } else {
                return 0;
            }
        }
        return flag != 2 ? res : -res;
    }


    // 正负数
    public List<Integer> sortNums(int[] nums){

        List<Integer> res = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < 0){
                l1.add(nums[i]);
            }else {
                l2.add(nums[i]);
            }
        }

        int i1 = l1.size(), i2 = l2.size();
        for (int i = 0; i < nums.length; i++) {
            if(i2 > 0){
                res.add(l2.get(i));
            }
            if(i1 > 0){
                res.add(l1.get(i));
            }
            i1--;
            i2--;
            if(i1 <= 0 && i2 <= 0){
                break;
            }
        }
        return res;
    }

    public static void main(String[] args){
        CharDeal deal = new CharDeal();
        System.out.println(deal.replaceSpace(new String[]{"cog","cacecar","car"}));
        System.out.println(deal.sortNums(new int[]{-1,2,-3,2,-4,-5,6,4,1,2}));
    }


}
