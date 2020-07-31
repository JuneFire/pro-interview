package src.leetcode.leetcode.arrays;

import java.util.*;

/**
 * 合并区间
 */
public class Solution_3 {
    public static void main(String[] args){
        int[][] nums = {{1,4},{0,5},{0,3},{9,10}}; // {{1,4},{0,4}}  {
        int[][] ans = sortMerge(nums);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }
    }

    /**
     * 对其进行左端点排序，若左端点在其前面的区间集合内，则区间左边取最小，右边取最大
     * @param intervals
     * @return
     */
    public static int[][] sortMerge(int[][] intervals){

        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null)
            return res.toArray(new int[0][]);
        // 对起点终点进行排序
        Arrays.sort(intervals,(a,b) -> a[0] - b[0]);   // 按左端点升序

        int i = 0;
        while (i < intervals.length){
            int left = intervals[i][0];
            int right = intervals[i][1];
            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {   // 若i+1区间左端点在 i 区间内
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            // 将现在的区间放进res里面
            res.add(new int[]{left, right});
            // 接着判断下一个区间
            i++;
        }


        return  res.toArray(new int[0][]);
    }



    /**
     * 栈  -- 失败
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {

        int[][] nums = {};

        if(intervals.length == 0)
            return nums;

        int a1 = intervals.length;



        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < a1 ; i++) {
            for (int j = 0; j < 2; j++){
                stack.push(intervals[i][j]);
            }
            if(i < (a1-1) && intervals[i+1][0] <= intervals[i][1] )
            {
                if(intervals[i+1][0] <= intervals[i][0])  // [1,4],[0,5]
                {
                    stack.pop();
                    stack.pop();
                    stack.push(intervals[i+1][0]);
                    stack.push(intervals[i+1][1]);
                }else {
                    stack.pop();
                    stack.push(intervals[i+1][1]);
                }
                i+=1;
            }
        }

        nums = new int[stack.size()/2][2];
        int len = stack.size()/2;
        while (!stack.empty()) {
            nums[len-1][1] = stack.pop();
            nums[len-1][0] = stack.pop();
            len--;
        }

        return nums;
    }


}
