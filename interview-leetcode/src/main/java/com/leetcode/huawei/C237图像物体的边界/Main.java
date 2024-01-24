package com.leetcode.huawei.C237图像物体的边界;
/*题目描述给定一个二维数组M行N列，二维数组里的数字代表图片的像素，为了简化问题，仅包含像素1和5两种像素，每种像素代表一个物体，2
        个物体相邻的格子为边界，求像素1代表的物体的边界个数。
        像素1代表的物体的边界指与像素5相邻的像素1的格子，边界相邻的属于同一个边界，相邻需要考虑8个方向 (上，下，左，右，左上，
        左下，右上，右下)。
        其他约束
        地图规格约束为：
        $0<M<100$
        $0<N<100$ 1) 如下图，与像素5的格子相邻的像素1的格子 (0,0) 、($0,1)、(0,2)、(1,0)、(1,2)、(2,0)、(2,1)、(2,2)、$

        (4,4) 、(4,5)、(5,4)为边界，另(0,0)、(0,1)、(0,2)、(1,0)、(1,2)、(2,0)、(2,1)、(2,2)相邻，为1个边界，
        输入描述第一行，行数M，列数N
        第二行开始，是M行N列的像素的二维数组，仅包含像素1和5
        输出描述
        像素1代表的物体的边界个数。
        如果没有边界输出0 (比如只存在像素1，或者只存在像素5) 。*/

import java.util.Scanner;

public class Main {
    // 定义移动方向数组，表示八个方向上的横纵坐标变化
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    // 定义一个二维数组，用于记录某个位置是否被访问过
    static int[][] visited;

    public static void main(String[] args) {
        // 使用Scanner类读取输入
        Scanner scanner = new Scanner(System.in);
        // 读取行数n和列数m
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        // 初始化地图数组mp和访问记录数组visited
        int[][] mp = new int[n][m];
        visited = new int[n][m];

        // 循环读取地图信息
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mp[i][j] = scanner.nextInt();
            }
        }

        // 初始化计数器，用于记录边界的数量
        int count = 0;
        // 遍历地图的每一个位置
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 如果当前位置是1，且是边界，且未被访问过，则进行深度优先搜索
                if (mp[i][j] == 1 && isBorder(i, j, mp, n, m) && visited[i][j] == 0) {
                    dfs(i, j, mp, n, m);
                    count++; // 每完成一次深度优先搜索，边界数量加1
                }
            }
        }

        // 输出边界的数量
        System.out.println(count);
    }

    // 深度优先搜索函数
    static void dfs(int x, int y, int[][] mp, int n, int m) {
        // 标记当前位置为已访问
        visited[x][y] = 1;
        // 遍历八个方向
        for (int i = 0; i < 8; i++) {
            // 计算移动后的新坐标
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 检查新坐标是否在地图范围内，是否为1，是否是边界，是否未被访问过
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && mp[nx][ny] == 1 && isBorder(nx, ny, mp, n, m) && visited[nx][ny] == 0) {
                // 递归进行深度优先搜索
                dfs(nx, ny, mp, n, m);
            }
        }
    }

    // 判断一个位置是否是边界的函数
    static boolean isBorder(int x, int y, int[][] mp, int n, int m) {
        // 遍历八个方向
        for (int i = 0; i < 8; i++) {
            // 计算移动后的新坐标
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 如果新坐标在地图范围内，且值为5，则当前位置是边界
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && mp[nx][ny] == 5) {
                return true;
            }
        }
        // 如果所有方向都不满足边界条件，则返回false
        return false;
    }
}
