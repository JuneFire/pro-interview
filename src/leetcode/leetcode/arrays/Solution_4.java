package src.leetcode.leetcode.arrays;

import java.util.Arrays;

/**
 * 旋转矩阵
 */
public class Solution_4 {

    public static void main(String[] args){
        int[][] nums = {{1,2,3},{4,5,6},{7,8,9}}; // {{1,4},{0,4}}  {
        rotate(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(Arrays.toString(nums[i]));
        }
    }


    public static void rotate(int[][] matrix) {

        if(matrix.length == 0 || matrix == null){
            return;
        }

        int N = matrix.length;

        int[][] newArr = new int[N][N];

        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < N; j++){
                newArr[j][N - i - 1] = matrix[i][j];
            }
        }
        matrix = newArr;
        for (int i = 0; i < newArr.length; i++) {
            System.out.println(Arrays.toString(newArr[i]));
        }
    }

}
