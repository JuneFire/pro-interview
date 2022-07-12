package leetcode.L300;

import java.util.HashMap;

/**
 * @Author: zkcheng
 * @Date: 2022/06/16/11:41
 * @Description:
 */
public class Solution_climbStairs_70 {

    /**
     * 爬楼梯，主要考虑第一步分布走一步和走两步的走法的之和。
     * @param n
     * @return
     */
    private HashMap<Integer, Integer> storeMap = new HashMap<Integer, Integer>();
    public int climbStairs(int n) {
        // 递归、hashmap
        if(n == 1) return 1;
        if(n == 2) return 2;
        if(null != storeMap && storeMap.get(n) != null){
            return storeMap.get(n);
        }else {
            int result = climbStairs(n - 1) + climbStairs(n - 2);
            storeMap.put(n, result);
            return result;
        }

        // 或者采用自底向上的循环叠加
//        if(n == 1) return 1;
//        if(n == 2) return 2;
//        int result = 0;
//        int pre = 2;
//        int prePre = 1;
//        for (int i = 3; i <= n; i++) {
//            result = pre + prePre;
//            prePre = pre;
//            pre = result;
//        }
//        return result;
    }

    public static void main(String[] args){
        Solution_climbStairs_70 solution = new Solution_climbStairs_70();
        System.out.println(solution.climbStairs(5));
    }
}
