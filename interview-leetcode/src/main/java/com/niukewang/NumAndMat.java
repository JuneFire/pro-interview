package main.java.niukewang;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zkCheng
 * @date 2021/12/22 14:45
 */
public class NumAndMat {

    private static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * 数组中重复的数字
     * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 要求时间复杂度 O(N)，空间复杂度 O(1)
     * (6, 3, 1, 0, 2, 5,4)
     * 解体思路，将num[i] 放入index为 i 的位置(nums[i] = i)，若该位置上已经被填充过了，说明有重复
     * */
    public int duplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i){
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                }
                swap(nums, i, nums[i]); // 把index 为 i 和 num[i] 的位置进行调换， 例如： [1,3,2,0,2,5]
            }
            swap(nums, i , nums[i]);  //[3,1,2,0,2,5]
        }
        return -1;
    }

    // 把num[i] 放在 num[num[i]]的位置
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];  // i = 0, j = 6 , t = num[i] == 6
        nums[i] = nums[j]; // num[0] = num[6]
        nums[j] = t;    // num[6] = 6
    }

    /**
     * Consider the following matrix:
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     *
     * Given target = 5, return true.
     * Given target = 20, return false.
     * @param target
     * @param matrix
     * @return
     * 要求时间复杂度 O(M + N)，空间复杂度 O(1)。其中 M 为行数，N 为 列数。
     * 思路:左边小于右边，上边小于下边， 从右上角开始遍历
     */
    public boolean Find(int target, int[][] matrix){
        int M = matrix.length;
        int N = matrix[0].length;
        int r = 0, l = N - 1; // 从右上角开始
        while(r < M && l > 0){
            if(target == matrix[r][l]){
                return true;
            }else if(target > matrix[r][l]) {
                r++;
            }else {
                l--;
            }
        }
        return false;
    }

    /**
     * 替换空格
     * 将字符中的空格替换成"%20"
     */
    public String replaceSpace(StringBuffer str){
        StringBuffer sb = new StringBuffer();
        char[] chars = str.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == ' '){
                sb.append("%20");
            }else {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 按顺时针的方向，从外到里打印矩阵的值
     * 解题思路：
     * 使用四个变量 r1, r2, c1, c2 分别存储上下左右边界值，从而定义当前最外层
     * 从左到右打印最上一行->从上到下打印最右一行->从右到左打印最下一行->从下到上打印最左一行
     * 应当注意只有在 r1 != r2 时才打印最下一行，也就是在当前最外层的行数大于 1 时才打印最下一行
     * 打印最左一行也要做同样处理。
     * 1  2  3  4
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16
     */

    public ArrayList<Integer> printMatrix(int[][] matrix){
        ArrayList<Integer> ret = new ArrayList<>();
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;  //r1 第一行， r2最后一行， c1 第一列， c2 最后一列
        while (r1 <= r2 && c1 <= c2){
            // 上
            for (int i = c1; i <= c2; i++) {
                ret.add(matrix[r1][i]);
            }
            // 右
            for (int i = r1+1; i < r2; i++) {
                ret.add(matrix[i][c2]);
            }
            if (r1 != r2)
            // 下
                for (int i = c2 - 1; i >= c1; i--){
                    ret.add(matrix[r2][i]);
            }
            if (c1 != c2)
                // 左
                for (int i = r2 - 1; i >= r1 ; i--) {
                    ret.add(matrix[i][c1]);
                }
            r1++; r2--; c1++; c2--;
        }

        return ret;
    }



    /**
     *    // 从String中找出字符只出现一次的字符,并返回它的位置
     * 解体思路：字符char ASCII 码只有 128 个字符，所以你懂的
     */
    public int  FirstNotRepeatingChar(String str){
        int[] cnt = new int[128];
        for (int i = 0; i < str.length(); i++) {
            cnt[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if(cnt[str.charAt(i)] == 1){
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args){
        NumAndMat numAndMat = new NumAndMat();
        StringBuffer sb = new StringBuffer("a bc ");
        System.out.println(numAndMat.replaceSpace(sb));

        System.out.println(numAndMat.FirstNotRepeatingChar("abcbd"));

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("a","b");

    }
}
