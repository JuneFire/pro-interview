package src.leetcode.leetcode.arrays;

import src.leetcode.leetcode.Solution;

public class Solution_1 {
    public static void main(String[] args){
//        int nums[] = {1,2,3,3};
        int nums[] = {};

        System.out.println(pivotIndex3(nums));
    }

    public static int pivotIndex3(int[] nums) {
        int sum = 0, leftsum = 0;
        for (int x: nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i])
                return i;
            leftsum += nums[i];
        }
        return -1;
    }


// 查找中心索引
    public static int pivotIndex2(int[] nums){
        int sum = 0, sumLeft = 0, sumRight = 0;
        for(int n : nums){
            sum += n;
        }

        for (int i = 0; i <nums.length ; i++) {

            if (i == 0){
                sumLeft = 0;
            }else {
                sumLeft += nums[i-1];
            }

            sumRight = sum - sumLeft - nums[i];

            if(sumLeft == sumRight){
                return i;
            }
        }


        return -1;
    }



    public static int pivotIndex(int[] nums) {

        /**
         * 1、 取中间index
         * 2、计算左边sum1，右边sum2
         * 3、若sum1>sum2,index 左移，否则右移。直到sum1 = sum2
         */
        if(nums.length == 0){
            return -1;
        }
        int dex = nums.length / 2;
        int a = sum(nums,0,dex-1);
        int b = sum(nums,dex+1,nums.length-1);
        if(a == b){
            return dex;
        }
        int index = nums.length / 2;
        int sum1 = sum(nums,0,index-1);
        int sum2 = sum(nums,index+1,nums.length-1);

        if(a > b){
            while (sum1 > sum2){
                index--;
                sum1 = sum(nums,0,index-1);
                sum2 = sum(nums,index+1,nums.length-1);
                if(sum1 == sum2)
                    return index;
            }
        }else if (a < b){
            while (sum1 < sum2){
                index++;
                sum1 = sum(nums,0,index-1);
                sum2 = sum(nums,index+1,nums.length-1);
                if(sum1 == sum2)
                    return index;
            }
        }

        return -1;
    }

    public static int sum(int[] nums,int left,int right){
        int sum = 0;
        while(left <= right){
            sum += nums[left++];
        }
        return sum;
    }
}
