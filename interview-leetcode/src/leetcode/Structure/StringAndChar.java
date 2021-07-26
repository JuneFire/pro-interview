package leetcode.Structure;

/**
 * @Author: zkcheng
 * @Date: 2021/07/04/21:03
 * @Description:
 */
public class StringAndChar {

    // 两个字符串包含的字符是否完全相同
    public boolean isAnagram(String s, String t) {
        int[] cnts = new int[26];
        for (char c : s.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            cnts[c - 'a']--;
        }
        for (int cnt : cnts) {
            if (cnt != 0) {
                return false;
            }
        }
        return true;
    }

    //
    public int longestPalindrome(String s){
        int[] count = new int[128];
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            char c = s.charAt(i);  // char c
            count[c]++;
        }

        int ans = 0;
        for (int v: count) {
            ans += v / 2 * 2;  // 假如v出现了五次 ，我们只取4次
            if (v % 2 == 1 && ans % 2 == 0) { // v是奇数且ans为偶数的时候，将ans变为奇数
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args){
        StringAndChar temp = new StringAndChar();
        System.out.println(temp.longestPalindrome(""));
        System.out.println(" ".length());
    }
}
