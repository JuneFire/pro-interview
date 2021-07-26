package leetcode.Mind;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zkcheng
 * @Date: 2021/06/21/21:59
 * @Description:
 */
public class Binary {

    /**
     * 1、计算在网格中从原点到特定点的最短路径长度
     * 1091. Shortest Path in Binary Matrix(Medium)
     */
    public int shortestPathBinaryMartix(int[][] grids){
        // 排错
        if(grids == null || grids.length == 0 || grids[0].length == 0){
            return -1;
        }
        // 点(x,y)的八个方向： 左上，正上,右上，正左，正右，左下，正下，右下
        int[][] direction = {{-1,1},{0,1},{1,1},{-1,0},{1,0},{-1,-1},{0,-1},{1,-1}};
        int m = grids.length, n = grids[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0,0));
        int pathLength = 0;
        while (!queue.isEmpty()){  // 入队出队
            int size = queue.size();
            pathLength++;
            while (size-- > 0){
                Pair<Integer, Integer> cur = queue.poll();
                int cr = cur.getKey(), cc = cur.getValue();
                if(grids[cr][cc] == 1){  // 不通的路直接跳过
                    continue;
                }
                if(cr == m - 1 && cc == n - 1){  //走完了
                    return pathLength;
                }
                grids[cr][cc] = 1; //标记为已经走过的路
                for (int[] d : direction){ //判断grids[cr][cc]有无路可走
                    int nr = cr + d[0], nc = cc + d[1];
                    if( nr < 0 || nr >= m || nc < 0 || nc >= n){ //此路不通
                        continue;
                    }
                    queue.add(new Pair<>(nr,nc)); //加入队列中继续判断
                }
            }
        }
        return -1;
    }

    // 二分查找
    private boolean binarySearch(int[] nums, int target){
        int l = 0, r = nums.length- 1;
        while(l <= r) {
            int mid =  l + (r - l) / 2;
            if(nums[mid] == target){
                return true;
            } else if(nums[mid] > target){
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    public boolean search(int[] nums, int target) {
        int k = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i-1] > nums[i]){
                k = i-1;
                break;
            }
        }
        if(target == nums[0]){
            return true;
        }else if(target > nums[0]){
            int[] right = new int[k+1];
            System.arraycopy(nums,0, right, 0, k + 1);
            return binarySearch(right, target);
        }else {
            int[] left = new int[nums.length  - 1 - k];
            System.arraycopy(nums,k + 1, left, 0, nums.length - 1 - k );
            return binarySearch(left,target);
        }

    }


    // 返回大于给定元素的最小值
    public char nextGreaterLetter(char[] letter, char target){
        int l = 0, r = letter.length - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if(letter[mid] <= target){  // 如果mid小于等于目标值，则再迭代几次，直到循环结束（当letter[mid + 1] 刚好大于 target的时候，循环就结束了）
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return l < letter.length ? letter[l] : letter[0];
    }


    // 一个有序数组只有一个数不出现两次，找出这个数
    //Input: [1, 1, 2, 3, 3, 4, 4, 8, 8]
    //Output: 2
    public int singleNonDuplicate(int[] nums){
        int l = 0, h = nums.length - 1;
        while (l < h){
            int mid = l + (h - l) / 2;
            if(mid % 2 == 1){
                mid--;     // mid取偶数位置
            }
            if(nums[mid] == nums[mid + 1]){  // mid的左半部分是正常的，这时往右边查找
                l = mid + 2;  //保证左下标是偶数
            }else {
                h = mid;
            }
        }
        return nums[l]; // 因为l是偶数，target的下标也是偶数
    }

    // 找到有序数组中target的区间的first
    public int findFirstDuplicate(int[] nums, int target){
        int l = 0, h = nums.length - 1;
        while (l < h){
            int mid = l + (h - l) / 2;
            if(nums[mid] < target){  // {1} , 0
                l = mid + 1;
            } else {
                h = mid;
            }
        }
        return l;
    }

    public int[] searchRange(int[] nums, int target){
        int index1 = findFirstDuplicate(nums, target);
        if (index1 >= nums.length || nums[index1] != target){
            return new int[]{-1, -1};
        }
        int index2 = index1;
        for (int i = index1; i < nums.length; i++){
            if(nums[i] == target){
                index2 = i;
            }else {
                break;
            }

        }
        return new int[]{index1,index2};
    }



    public static void main(String[] args){
        Binary binary = new Binary();
//        System.out.println(binary.shortestPathBinaryMartix(new int[][]{{0,0,0},{1,1,0},{1,1,0}}));
        System.out.println(binary.searchRange(new int[]{1,2,3}, 2));
    }

}
