package com.leetcode.huawei.C228亲子游戏;
/*
// 题目描述宝宝和妈妈参加亲子游戏，在一个二维矩阵 (N^*N) 的格子地图上，宝宝和妈妈抽签决定各自的位置，地图上每个格子有不同的糖果数
//         量，部分格子有障碍物。
//         游戏规则是妈妈必须在最短的时间(每个单位时间只能走一步)到达宝宝的位置，路上的所有糖果都可以拿走，不能走障碍物的格子，只
//         能上下左右走。
//         请问妈妈在最短到达宝宝位置的时间内最多拿到多少糖果(优先考虑最短时间到达的情况下尽可能多拿糖果)。
//         输入描述
//         第一行输入为 N, N 表示二维矩阵的大小
//         之后 N 行，每行有 N 个值，表格矩阵每个位置的值，其中：
//         -3:妈妈
//         -2:宝宝
//         -1: 障碍
//         >=0: 糖果数 (0表示没有糖果，但是可以走)

//         输出描述输出妈妈在最短到达宝宝位置的时间内最多拿到多少糖果，行末无多余空格
//         用例
//         输入
//         4
//         3 2 1 -3
//         1 -1 1 1
//         1 1 -1 2
//         -2 1 2 3

//         输出
//         9
        */
import java.util.*;

// 主类
public class Main {
    // 定义四个方向移动的坐标变化（上、右、下、左）
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    // 主函数
    public static void main(String[] args) {
        // 使用 Scanner 读取输入数据
        Scanner scanner = new Scanner(System.in);
        // 读取矩阵的大小
        int N = scanner.nextInt();
        // 初始化矩阵
        int[][] grid = new int[N][N];
        // 初始化访问数组，记录到达每个位置的最短步数和糖果数量
        int[][][] visited = new int[N][N][2]; // [x][y][0] 代表步数，[x][y][1] 代表糖果数
        // 将访问数组初始化为 -1
        for (int[][] layer : visited) {
            for (int[] cell : layer) {
                Arrays.fill(cell, -1);
            }
        }
        // 初始化队列，用于 BFS 搜索
        Queue<Node> queue = new LinkedList<>();
        // 初始化起点
        Node start = null;

        // 读取矩阵信息，并找到起点位置
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = scanner.nextInt();
                if (grid[i][j] == -3) { // 如果是起点
                    start = new Node(i, j, 0, 0); // 创建起点节点
                    visited[i][j][0] = 0; // 起点的步数为 0
                    visited[i][j][1] = 0; // 起点的糖果数为 0
                }
            }
        }
        // 关闭 Scanner
        scanner.close();

        // 将起点加入队列
        queue.add(start);
        // 初始化最大糖果数
        int maxCandies = 0;
        int flag = 0;
        // BFS 搜索
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            // 如果到达终点，更新最大糖果数
            if (grid[current.x][current.y] == -2) {
                flag = 1;
                maxCandies = Math.max(maxCandies, current.candies);
                continue;
            }

            // 遍历四个方向
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // 检查新位置是否有效
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && grid[nx][ny] != -1) {
                    // 计算新位置的糖果数和步数
                    int newCandies = current.candies + Math.max(grid[nx][ny], 0);
                    int newSteps = current.steps + 1;
                    // 如果新位置未访问过，或者可以以更少的步数到达，或者步数相同但糖果数更多，则更新信息并加入队列
                    if (visited[nx][ny][0] == -1 || visited[nx][ny][0] > newSteps ||
                            (visited[nx][ny][0] == newSteps && visited[nx][ny][1] < newCandies)) {
                        queue.add(new Node(nx, ny, newCandies, newSteps));
                        visited[nx][ny][0] = newSteps;
                        visited[nx][ny][1] = newCandies;
                    }
                }
            }
        }
        if(flag == 0){
            maxCandies = -1;
        }
        // 输出最大糖果数，如果没有到达终点则输出 -1
        System.out.println(maxCandies >= 0 ? maxCandies : -1);
    }

    // 节点类，用于表示 BFS 中的每个状态
    static class Node {
        int x, y, candies, steps;

        // 节点构造函数
        public Node(int x, int y, int candies, int steps) {
            this.x = x;
            this.y = y;
            this.candies = candies;
            this.steps = steps;
        }
    }
}

