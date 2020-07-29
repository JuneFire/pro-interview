package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 刘建广
 * @Date 2020/7/16 15:24
 * @Version 1.0
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(),ans = 0;
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        for ( int start = 0,end =0; end < n; end++){
            char c = s.charAt(end);
            if (map.containsKey(c)){
                start = Math.max(map.get(c), start);
            }

            ans = Math.max(ans, end - start + 1);
            map.put(c, end + 1);
        }
        return ans;
    }

    public static void main(String[] args){
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.lengthOfLongestSubstring("a"));

    }
}
