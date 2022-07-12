package leetcode.L300;

import java.util.Stack;

/**
 * @Author: zkcheng
 * @Date: 2022/06/24/17:36
 * @Description:
 */
public class Solution_DecodeString_394 {
    /**
     * "3[a2[c]]" --> accaccacc
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        // 遇见 "[" 入栈， "]" 出栈
        String res = "";  // 当前字符
        Stack<String> resStack = new Stack<>(); // 存储字符
        Stack<Integer> countStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()){
            char curr = s.charAt(idx);
            /**
             * 处理数字
             */
            if(Character.isDigit(curr)){
                StringBuilder ret = new StringBuilder();
                while (Character.isDigit(s.charAt(idx))){
                    ret.append(s.charAt(idx++));
                }
                countStack.push(Integer.valueOf(ret.toString()));
            }
            /**
             * 处理字符
             */
            else if('[' == (curr)){
                // 处理 "["
                resStack.push(res);
                idx++;
                res = "";
            }
            else if (']' == curr){
                StringBuilder temp = new StringBuilder();
                temp.append(resStack.pop());
                int count = countStack.pop();
                for (int i = 0; i < count; i++) {
                    temp.append(res);  // 重复目前的string
                }
                res = temp.toString();
                idx++;
            }
            // 普通字符
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

    public static void main(String[] args){
        System.out.println(decodeString("3[a2[w]]2[bc]qwe"));
    }
}
