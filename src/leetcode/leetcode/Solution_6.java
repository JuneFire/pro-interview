

import java.util.Arrays;

public class Solution_6 {

    public int maxIncreaseKeepingSkyline(int[][] grid) {

        if (grid == null ||grid.length != grid[0].length || grid.length < 1 || grid.length > 50) throw new IllegalArgumentException("ERROR PARAM");

        int newHigh = 0;

        int[] bottom = new int[grid.length], right = new int[grid.length];

        for (int m = 0; m < grid.length; m++){
            for (int n = 0; n < grid[m].length; n++){
                bottom[m] = Math.max(bottom[m],grid[n][m]);
                right[n] = Math.max(right[n],grid[n][m]);
            }
        }

        System.out.println("bottom = " + Arrays.toString(bottom));
        System.out.println("right = " + Arrays.toString(right));

        for (int m = 0; m < right.length; m++){
            for (int n = 0; n < bottom.length; n++){
                newHigh += Math.min(right[m],bottom[n]) - grid[m][n];
                System.out.print(Math.min(right[m],bottom[n]) + " ");
            }
            System.out.println();
        }

        return newHigh;
    }

    public static void main(String[] args){

        Solution_6 solution = new Solution_6();
        int[][] grid = {{3, 0, 8, 4}, {2, 4, 5, 7},{9, 2, 6, 3},{0, 3, 1, 0}};
        System.out.println("sum = " + solution.maxIncreaseKeepingSkyline(grid));


    }

}
