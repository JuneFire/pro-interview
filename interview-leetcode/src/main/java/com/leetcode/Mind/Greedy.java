package com.leetcode.Mind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 贪心算法
 * @Author: zkcheng
 * @Date: 2021/06/16/21:52
 * @Description:
 */
public class Greedy {

    /**
     * 1. 分配饼干
     * 455. Assign Cookies (Easy)
     * @param grid 小孩满足度
     * @param size 每块饼干大小
     */
    public int findContentChildren(int[] grid, int[] size){
        if(grid == null || size == null) return 0;
        Arrays.sort(grid);
        Arrays.sort(size);
        int gi = 0, si = 0;
        while (gi < grid.length && si < size.length){
            if(grid[gi] <= size[si]){
                gi++;
            }
            si++;
        }
        return gi;
    }

    /**
     * 2. 不重叠的区间个数
     * 435. Non-overlapping Intervals (Medium)
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int cnt = 1;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                continue;
            }
            end = intervals[i][1];
            cnt++;
        }
        return intervals.length - cnt;
    }

    /**
     * 3. 投飞镖刺破气球
     * 452. Minimum Number of Arrows to Burst Balloons (Medium)
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int cnt = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) {
                continue;
            }
            cnt++;
            end = points[i][1];
        }
        return cnt;
    }

    /**
     * 4. 根据身高和序号重组队列
     * 406. Queue Reconstruction by Height(Medium)
     */
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        List<int[]> queue = new ArrayList<>();
        for (int[] p : people) {
            queue.add(p[1], p);
        }
        return queue.toArray(new int[queue.size()][]);
    }

    /**
     * 5. 买卖股票最大的收益
     * 121. Best Time to Buy and Sell Stock (Easy)
     */
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int soFarMin = prices[0];
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (soFarMin > prices[i]) soFarMin = prices[i];
            else max = Math.max(max, prices[i] - soFarMin);
        }
        return max;
    }

    /**
     * 6. 买卖股票的最大收益 II
     * 122. Best Time to Buy and Sell Stock II (Easy)
     */
    public int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += (prices[i] - prices[i - 1]);
            }
        }
        return profit;
    }

    /**
     * 7. 种植花朵
     * 605. Can Place Flowers (Easy)
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int cnt = 0;
        for (int i = 0; i < len && cnt < n; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            int pre = i == 0 ? 0 : flowerbed[i - 1];
            int next = i == len - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 && next == 0) {
                cnt++;
                flowerbed[i] = 1;
            }
        }
        return cnt >= n;
    }

    /**
     * 8. 判断是否为子序列
     * 392. Is Subsequence (Medium)
     */
//    public boolean isSubsequence(String s, String t){
//        int i = 0, j = 0;
//        while (i < s.length() && j < t.length()){
//           if(s.charAt(i) == t.charAt(j)){
//               j++;
//           }
//           i++;
//       }
//       return j == t.length();
//    }

    /**
     * s = "abc", t = "ahbgdc"
     * Return true.
     */
    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            index = t.indexOf(c, index + 1); //fromIndex是主字符串在其中我们方法将开始搜索字符的位置。
            if (index == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 9. 修改一个数成为非递减数组
     * 665. Non-decreasing Array (Easy)
     * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
     * [3, 4, 2, 5]- true
     * 优先考虑令 nums[i - 1] = nums[i]，因为如果修改 nums[i] = nums[i - 1] 的话，那么 nums[i] 这个数会变大，就有可能比 nums[i + 1] 大，从而影响了后续操作。
     * 比较特别的情况就是 nums[i] < nums[i - 2]，修改 nums[i - 1] = nums[i] 不能使数组成为非递减数组，只能修改 nums[i] = nums[i - 1]。
     */
    public boolean checkPossibility(int[] nums){
        int cnt = 0;
        for (int i = 1; i < nums.length && cnt < 2; i++) {
            if(nums[i] >= nums[i - 1]){
                continue;
            }
            cnt++;
            if(i - 2 >= 0 && nums[i - 2] > nums[i]){
                nums[i] = nums[i - 1];  //此时 num[i - 1] > num[i]
            }else {
                nums[i - 1] = nums[i]; //前后掉换位置 方便下次判断
            }
        }
        return cnt <= 1;
    }

    /**
     * 763. 划分字母区间
     */
    public List<Integer> partitionLabels(String s) {
        if(s == null || s.length() == 0){
            return null;
        }
        // 存储每个字母的最大last
        int[] charLabelsArray = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charLabelsArray[s.charAt(i) - 'a'] = i;
        }
        List<Integer> result = new ArrayList<>();

        int firstIndex = 0;
        while (firstIndex < s.length()){
            int lastIndex = firstIndex;
            for (int i = firstIndex; i < s.length() && i <= lastIndex; i++){
                // 取last，比较first-last 区间，更新last，直到其不能更新
                if(charLabelsArray[s.charAt(i) - 'a'] > lastIndex){
                    lastIndex = charLabelsArray[s.charAt(i) - 'a'];
                }
            }
            result.add(lastIndex - firstIndex + 1); // 存区间
            firstIndex = lastIndex + 1;
        }
        return result;
    }

    public static void main(String[] args){
        Greedy greedy = new Greedy();
        System.out.println(greedy.partitionLabels("ababcbacadefegdehijhklij"));
    }
}











