package src.leetcode.leetcode.arrays;

import java.util.Arrays;

public class Solution_2 {

    public static void main(String[] args){
        int[] nums = {1,2,4};
        System.out.println(searchInsert(nums,5));

        System.out.println(Arrays.toString(insertArr(nums,4,2)));
    }


    public static  int searchInsert(int[] nums, int target) {

        int preIndex = 0;
        if (nums.length == 0 || nums[0] > target)   // 第一个位置大于目标值，直接插入
            return 0;

        while (preIndex < nums.length){
            if (nums[preIndex] == target)
                return preIndex;
            if (target < nums[preIndex])
                return preIndex;
            preIndex++;
        }
        return preIndex;
    }

    /**
     * 插入算法
     * @param nums
     * @param index
     * @param target
     * @return
     */
    public static int[] insertArr(int[] nums,int index,int target){
        if(index > nums.length){
            return null;
        }
        int arr[] = new int[nums.length+1];
        for (int i = 0; i < nums.length ; i++) {
            if(i < index)
                arr[i] = nums[i];
            else
                arr[i+1] = nums[i];
        }
        arr[index] = target;
        return arr;
    }

}
