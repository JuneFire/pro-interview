package com.leetcode.huawei.C202可以组成网络的服务器;

import java.util.Scanner;

/*题目描述：可以组成网络的服务器 (本题分值200) 在一个机房中，服务器的位置标识在 n*m 的整数矩阵网格中，1 表示单元格上有服务器，0 表示没有。如果两台服务器位于同一行或者同
        一列中紧邻的位置，则认为它们之间可以组成一个局域网。
        请你统计机房中最大的局域网包含的服务器个数。
        输入描述
        第一行输入两个正整数，n和m，0<n,m<=100
        之后为n*m的二维数组，代表服务器信息
        输出描述
        最大局域网包含的服务器个数。
        用例1
        输入
        2 2
        1 0
        1 1
        输出
         3
        [0][0]、[1][0]、[1][1]三台服务器相互连接，可以组成局域网*/
public class Main {
    private static int[][] directions = {{0,1},{0, -1},{1,0},{-1,0}};

    public static void main(String[] ar) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col< m; col++) {
                matrix[row][col] = scanner.nextInt();
            }
        }

        int max = 0;
        boolean[][] visited = new boolean[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col< m; col++) {
                if (matrix[row][col] == 1 && !visited[row][col]) {
                    max = Math.max(max, dfs(matrix, row, col, visited));
                }
            }
        }
        System.out.println(max);
    }
    private static int dfs(int[][] matrix, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length
                || matrix[row][col] == 0 || visited[row][col]) {
            return 0;
        }
        visited[row][col] = true;
        int count = 1;
        for (int[] direction : directions) {
            int nextRow = row + direction[0], nextCol = col + direction[1];
                count += dfs(matrix, nextRow, nextCol, visited);

        }
        return count;
    }
}
