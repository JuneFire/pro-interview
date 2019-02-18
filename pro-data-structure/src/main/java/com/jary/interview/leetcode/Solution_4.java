package com.jary.interview.leetcode;

import javafx.util.Pair;

import java.util.*;

public class Solution_4 {

    public int repeatedNTimes(int[] A) {

        if (A == null || A.length ==0 || A.length % 2 != 0 || A.length > 10000 || A.length < 4) return 0;

        Map<Integer,Integer> map = new HashMap<>();

        int key,value;

        for (int i = 0; i < A.length; i++){
            if (A[i] >= 10000 || A[i] < 0) throw new IllegalArgumentException("ERROR PARAM");
            if (map.containsKey(A[i])){
                value = map.get(A[i]);
                value++;
            } else value = 1;
            map.put(A[i], value);
        }

        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            if (entry.getValue() == A.length / 2) return entry.getKey();
        }

        return 0;
    }

    public static void main(String[] args){
        int[] A = {5,1,5,2,5,3,5,4};

        Solution_4 solution = new Solution_4();
        System.out.println("num = " + solution.repeatedNTimes(A));

    }

}
