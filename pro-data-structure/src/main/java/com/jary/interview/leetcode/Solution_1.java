package com.jary.interview.leetcode;

public class Solution_1 {

    public int numJewelsInStones(String J, String S) {

        int i = 0,sum = 0;

        char[] jChars = J.toCharArray(),sChars = S.toCharArray();

        for (int m = 0; m < jChars.length; m++){
            for (int n = 0; n < sChars.length; n++){
                if (jChars[m] == sChars[n]) sum++;
            }
        }

        return sum;
    }

    public static void main(String[] args){
        Solution_1 solution = new Solution_1();
        String J = "z", S = "ZZ";
        System.out.println("sum = " + solution.numJewelsInStones(J,S));
    }

}
