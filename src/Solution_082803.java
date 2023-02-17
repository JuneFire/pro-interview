package src;

import java.util.Scanner;

/**
 * @author zkCheng
 * @date 2022/8/28 10:06
 */
public class Solution_082803 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] nums = new int[t];
        for(int i = 0; i < t; i++){
            nums[i] = sc.nextInt();
        }
        System.out.println(maxValueAfterRev(nums));
    }

    public static int maxValueAfterRev(int[] nums){
        int ans = 0, sum = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            sum += Math.abs(nums[i] - nums[i + 1]);
        }

        int mn = Math.max(nums[0], nums[1]);
        int mx = Math.min(nums[0], nums[1]);
        for (int i = 1; i < n; i++) {
            int a1 = Math.min(nums[i], nums[i - 1]);
            int a2 = Math.max(nums[i], nums[i - 1]);
            if(mn < a1) ans = Math.max(ans, a1 - mn);
            if(mx > a2) ans = Math.max(ans, mx - a2);
            mn = Math.min(mn, a2);
            mx = Math.max(mx, a1);
        }
        ans *= 2;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, Math.abs(nums[i] - nums[0]) - Math.abs(nums[i] - nums[i - 1]));
            ans = Math.max(ans, Math.abs(nums[n - i - 1] - nums[n - 1])
                    - Math.abs(nums[n - i] - nums[n - i - 1]));
        }
        return sum + ans;
    }

    // 动态规划
    public static int maxSubArray(int[] nums){
        int Max = nums[0];
        int pre = 0;
        int cur = 0;
        for(int num : nums){
            cur = num;
            if(pre > 0){                //如果前面的和>0，++
                cur += pre;
            }
            if(cur > Max){
                Max = cur;
            }
            pre = cur;
        }
        return Max;
    }


    // 求子数组最大和
    public static int res(int[] nums){
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(0, nums[i - 1]);
            temp = Math.max(temp, nums[i]);
        }
        return temp;
    }
}
