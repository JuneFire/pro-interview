package leetcode;

import java.util.Stack;

public class Solution_11 {

    public int minAddToMakeValid(String S) {

        if (S == null || S.length() == 0) return 0;

        Stack<Character> stack = new Stack<>();
        char[] chars = S.toCharArray();
        stack.push(chars[0]);
        char curChar = 0;
        for (int i = 1; i < chars.length; i++){
            if (stack.size() != 0) curChar = stack.peek();
            if (curChar == '(' && chars[i] == ')') {stack.pop();curChar = '0';}
            else stack.push(chars[i]);;
        }
        return stack.size();
    }

    public static void main(String[] args){
        Solution_11 solution = new Solution_11();
        String S = "()";
        System.out.println("size = " + solution.minAddToMakeValid(S));
    }

}
