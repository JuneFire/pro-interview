package com.leetcode.huawei.C59小华地图寻宝;
/*小华按照地图去寻宝，地图上被划分成 m 行和 n 列的方格，横纵坐标范围分别是[0,n-1] 和[0,m-1]。在横坐标和纵坐标的数位之和不大于 k 的方格中存在黄金 (每个方格中仅存在一克黄金), 但横坐标和纵坐标数位之和大于 k 的方格存在
        危险不可进入。小华从入口(0,0)进入，任何时候只能向左，右，上，下四个方向移动一格。
        请问小华最多能获得多少克黄金？
        输入描述
        坐标取值范围如下：
        $\cdot0\leq m\leq50$
        $\cdot0\leq n\leq50$ k 的取值范围如下：

        $\cdot0\leq k\leq100$
        输入中包含3个宇数，分别是m，n,k
        输出描述
        输出小华最多能获得多少克黄金*/

import java.util.*;

public class Main {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();
        visited = new boolean[m][n];
        System.out.println(dfs(0, 0, m, n, k));
    }

    static int dfs(int x, int y, int m, int n, int k) {
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || digitSum(x) + digitSum(y) > k) {
            return 0;
        }
        visited[x][y] = true;
/*
        int res = 1;
        for (int[] dir : dirs) {
            res += dfs(x + dir[0], y + dir[1], m, n, k);
        }
        return res;
*/

        return 1+ dfs(x+1, y, m, n, k) + dfs(x-1, y, m, n, k) + dfs(x, y+1, m, n, k) + dfs(x, y-1, m, n, k);
    }

    static int digitSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
