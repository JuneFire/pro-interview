package com.leetcode.huawei.C231寻找最优的路测线路;
/*
题目描述评估一个网络的信号质量，其中一个做法是将网络划分为栅格，然后对每个栅格的信号质量计算。
        路测的时候，希望选择一条信号最好的路线 (彼此相连的栅格集合) 进行演示。
        现给出 R 行 C 列的整数数组 Cov, 每个单元格的数值 S 即为该栅格的信号质量 (日归一化，无单位，值越大信号越好)
        要求从[0,0] 到[R-1,C-1]设计一条最优路测路线。返回该路线得分。
        规则：
        ·路测路线可以上下左右四个方向，不能对角
        · 路线的评分是以路线上信号最差的栅格为准的，例如路径 8 4 5 9 的值为4，该线路评分为4。线路最优表示该条线路的评分最
        高。
        输入描述
        一行表示栅格的行数 R
        第二行表示栅格的列数 C 第三行开始，每一行表示栅格地图一行的信号值，如5 4 5

        输出描述
        最优路线的得分
*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    // 定义一个内部类表示网格中的一个单元格
    static class Cell {
        int row, col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // 使用广度优先搜索（BFS）检查是否存在一条从起点到终点的路径，路径上所有单元格的信号质量都不低于minSignal
    private static boolean bfs(int[][] Cov, int minSignal) {
        int R = Cov.length, C = Cov[0].length;
        // 如果起点或终点的信号质量低于minSignal，直接返回false
        if (Cov[0][0] < minSignal || Cov[R - 1][C - 1] < minSignal) {
            return false;
        }

        // visited数组用于记录哪些单元格已经被访问过，避免重复访问
        boolean[][] visited = new boolean[R][C];
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(0, 0));
        visited[0][0] = true;

        // dr和dc数组用于表示从当前单元格向四个方向（上下左右）移动的行和列的变化量
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            // 如果到达终点，返回true
            if (cell.row == R - 1 && cell.col == C - 1) {
                return true;
            }

            // 否则，尝试向四个方向移动
            for (int i = 0; i < 4; i++) {
                int nr = cell.row + dr[i];
                int nc = cell.col + dc[i];

                // 如果新的单元格在网格内，且没有被访问过，且信号质量不低于minSignal，将其加入队列并标记为已访问
                if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && Cov[nr][nc] >= minSignal) {
                    queue.add(new Cell(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }

        // 如果没有找到有效路径，返回false
        return false;
    }

    // 使用二分搜索找到最大的满足条件的信号质量
    private static int binarySearch(int[][] Cov, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // 如果存在一条有效路径，尝试更高的信号质量
            if (bfs(Cov, mid)) {
                low = mid + 1;
            } else { // 否则，降低信号质量
                high = mid - 1;
            }
        }
        // 返回最大的满足条件的信号质量
        return high;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int R = scanner.nextInt();
        int C = scanner.nextInt();
        int[][] Cov = new int[R][C];

        int minSignal = Integer.MAX_VALUE;
        int maxSignal = Integer.MIN_VALUE;

        // 读取网格数据，并记录信号质量的最小值和最大值
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                Cov[i][j] = scanner.nextInt();
                minSignal = Math.min(minSignal, Cov[i][j]);
                maxSignal = Math.max(maxSignal, Cov[i][j]);
            }
        }
        scanner.close();

        // 输出最大的满足条件的信号质量
        System.out.println(binarySearch(Cov, minSignal, maxSignal));
    }
}
