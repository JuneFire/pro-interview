package main.java.leetcode.DP;

/**
 * @author zkCheng
 * @date 2022/10/27 14:49
 */
public class Dynamic {

    // 斐波那契数列
    public static int func(int n){
        if(n < 2) return n;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 爬楼梯
    public static int climbStairs(int n){
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int climbStairs2(int n){
        if(n == 1) return 1;
        if(n == 2) return 2;
        int[] dp = new int[3];
        dp[1] = 1;
        dp[2] = 2;
        int sum = 0;
        for (int i = 3; i <= n; i++){
            sum = dp[1] + dp[2];
            dp[1] = dp[2];
            dp[2] = sum;
        }
        return sum;
    }

    public static void main(String[] args){
        System.out.println(climbStairs(10));
    }
}
