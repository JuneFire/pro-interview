package com.leetcode.Structure;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: zkcheng
 * @Date: 2021/07/04/19:14
 * @Description:
 */
public class HashTables {

    // Two sum
    public int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums[i]; i++) {
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }else {
                map.put(nums[i], i);
            }
        }
        return null;
    }


    //返回最长连续序列长度

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            hashSet.add(nums[i]);
        }
        int longestStreak = 0;
        for (int num : hashSet) {
            if(!hashSet.contains(num - 1)){  //保证 num 是最小的那位
                int curNum = num;
                int curSteak = 0;
                while (hashSet.contains(curNum++)){
//                    curNum++;
                    curSteak++;
                }
                longestStreak = Math.max(longestStreak, curSteak);
            }

        }
        return longestStreak;
    }

    public static void main(String[] args){
        HashTables hashTables = new HashTables();
        System.out.println(hashTables.longestConsecutive(new int[]{100,4,200,1,3,2}));
    }
}
