package src.leetcode.leetcode.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 零数组
 */
public class Solution_5 {

    public static void main(String[] args) {
        int[][] nums = {{1,0,3},{4,5,0},{7,8,9}};
        setZeroes(nums);
    }

    public static void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[0].length ; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (Integer row : rows){
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[row][i] = 0;
            }
        }

        for (Integer col : cols) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][col] = 0;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
