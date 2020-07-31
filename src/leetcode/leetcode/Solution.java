package src.leetcode.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author 刘建广
 * @Date 2020/7/15 22:15
 * @Version 1.0
 */
public class Solution {


    public int[] twoSum(int[] nums, int target) {
        int[] a = new int[2];

        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            int b = target - nums[i];
            if(hashMap.containsKey(b)){
                a[0] = hashMap.get(b);
                a[1] = i;
                break;
            }else {
                hashMap.put(nums[i],i);
            }
        }

        return a;
    }

    public static void main(String[] args){
        int[] nums = new int[]{15, 11, 2, 7};
        int target = 9;
        Solution solution = new Solution();
        int[] ints =  solution.twoSum(nums, target);
        System.out.println("答案："+ Arrays.toString(ints));
    }
}
