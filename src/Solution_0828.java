package src;

import java.util.Scanner;

/**
 * @author zkCheng
 * @date 2022/8/28 10:06
 */
public class Solution_0828 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 0; i < t; i++){
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = sc.nextInt();
            }
            maxsum(nums);
        }
    }

    public static void maxsum(int[] num){
        if(num ==  null || num.length == 0)
            return;
        int count = num[0];
        int count2 = 0;
        int index1 = 0, index2 = 0, id = 0;
        for (int i = 0; i < num.length; i++) {
//            if(num[i] == 0){
//                count = 0;
//                continue;
//            }
//            if(count == 0){
//                count = num[i];
//                if(count > count2){
//                    id = 0;
//                }
//
//            }else {
//                count = num[i] * count;
//                id++;
//            }
////            count2 = Math.max(count, count2);
//            if(count2 < count){
//                count2 = count;
//                index2 = i + 1;
//            }

        }

        index1 = index2 - id;
        System.out.print(index1 + " ");
        System.out.println(index2);
    }


    public int  maxPore(int[] nums){
        int max1, max2;
        int index1 , index2;
        max2 = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            max1 = nums[i];
            for (int j = i; j <= nums.length; j++) {
                if(j > i){
                    max1 *= nums[j];
                }
                if(max2 < max1){
                    max2 = max1;
                    index2 = j;
                }
            }
        }
        return max2;
    }
}
