package com.leetcode.huawei.C207欢乐的周末;
/*题目描述小华和小为是很要好的朋友，他们约定阔末一起吃饭。
        通过手机交流，他们在地图上选择了多个聚餐地点(由于自然地形等原因，部分聚餐地点不可达),求小华和小为都能到达的聚餐地点有
        多少个？
        输入描述
        第一行输入m和n，m代表地图的长度，n代表地图的宽度。
        第二行开始具体输入地图信息，地图信息包含：
        0 为通畅的道路
        1 为障碍物 (且仅1为障碍物)
        2 为小华或者小为，地图中必定有且仅有2个 (非障碍物)
        3 为被选中的聚餐地点 (非障碍物)
        输出描述
        可以被两方都到达的聚餐地点数量，行末无空格。
4 4
2 1 0 3
0 1 2 1
0 3 0 0
0 0 0 0
        */

import java.util.*;

public class Main {
    /**
     * 读取地图的大小m和n。
     * 读取地图信息，同时记录小华和小为的位置，以及所有聚餐地点的位置。
     * 对于小华和小为，分别使用广度优先搜索（BFS）或深度优先搜索（DFS）来找到他们可以到达的所有聚餐地点。
     * 比较小华和小为可以到达的聚餐地点，找出他们共同可以到达的聚餐地点的数量
     * @param args
     */
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];
        int[] start1 = new int[2], start2 = new int[2];
        List<int[]> targets = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if (grid[i][j] == 2) {  //
                    if (start1[0] == 0 && start1[1] == 0) {
                        start1 = new int[]{i, j};
                    } else {
                        start2 = new int[]{i, j};
                    }
                } else if (grid[i][j] == 3) {
                    targets.add(new int[]{i, j});
                }
            }
        }
        Set<String> reachable1 = bfs(grid, start1[0], start1[1]); // a 所有能到达的点
        Set<String> reachable2 = bfs(grid, start2[0], start2[1]); // b 所有能到达的点
        int count = 0;
        for (int[] target : targets) { // 目标位置
            if (reachable1.contains(target[0] + "," + target[1]) && reachable2.contains(target[0] + "," + target[1])) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static Set<String> bfs(int[][] grid, int x, int y) {
        Set<String> reachable = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] != 1) {
                    if (reachable.add(nx + "," + ny)) {
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return reachable;
    }
}

/*

import java.util.Scanner;
        import java.util.ArrayList;
        import java.util.List;

public class Main {
    // 定义四个方向的偏移量（上、下、左、右）
    private static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    // 深度优先搜索函数
    private static boolean dfs(int currX, int currY, int targetX, int targetY, int[][] map, boolean[][][] visited, int person) {
        // 如果当前位置就是目标位置，返回true
        if (currX == targetX && currY == targetY) {
            return true;
        }

        // 遍历四个方向
        for (int[] dir : dirs) {
            int nextX = currX + dir[0], nextY = currY + dir[1];
            // 如果下一个位置超出地图范围，或者是障碍物，或者已经访问过，跳过
            if (nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map[0].length || map[nextX][nextY] == 1 || visited[nextX][nextY][person]) {
                continue;
            }

            // 标记下一个位置为已访问
            visited[nextX][nextY][person] = true;
            // 递归搜索下一个位置
            if (dfs(nextX, nextY, targetX, targetY, map, visited, person)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // 输入初始化
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] map = new int[m][n];
        // 使用三维数组visited来记录每个人访问过的位置
        boolean[][][] visited = new boolean[m][n][2];
        List<int[]> persons = new ArrayList<>();
        List<int[]> targets = new ArrayList<>();
        // 读取地图信息，并记录小华和小为的位置以及聚餐地点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = scanner.nextInt();
                if (map[i][j] == 2) {
                    persons.add(new int[]{i, j});
                } else if (map[i][j] == 3) {
                    targets.add(new int[]{i, j});
                }
            }
        }

        // 获取小华和小为的位置
        int[] xiaohua = persons.get(0);
        int[] xiaowei = persons.get(1);
        int res = 0;
        // 遍历所有聚餐地点
        for (int[] target : targets) {
            // 重置visited数组
            visited = new boolean[m][n][2];
            // 判断小华是否能到达目标位置
            if (dfs(xiaohua[0], xiaohua[1], target[0], target[1], map, visited, 0)) {
                // 重置visited数组
                visited = new boolean[m][n][2];
                // 判断小为是否能到达目标位置
                if (dfs(xiaowei[0], xiaowei[1], target[0], target[1], map, visited, 1)) {
                    // 如果两个人都能到达目标位置，结果加1
                    res++;
                }
            }
        }
        // 输出可以被两人都到达的聚餐地点数量
        System.out.println(res);

        scanner.close();
    }
}
*/

