package com.leetcode.huawei.low;

import java.util.Arrays;

public class findFriend_shuzu {

    public static void main(String[] args){
        int[] height = new  int[]{123 ,124 ,125 ,121 ,119 ,122 ,126 ,123};
        int[] friend = new int[height.length];
        for (int i = 0; i < height.length - 1; i++) {
            int res = findFriend(height, i);
            friend[i] = res;
        }
        System.out.println(Arrays.toString(friend));

    }

    public static int findFriend(int[] height, int index){
        int gaodu = height[index];
        for (int i = index + 1; i < height.length; i++) {
            if(height[i] > gaodu){
                return i;
            }
        }
        return 0;
    }
}
