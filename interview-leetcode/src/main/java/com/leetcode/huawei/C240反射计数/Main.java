package com.leetcode.huawei.C240反射计数;
/*
题自描述给定一个包含 0 和 1 的二维矩阵，给定一个初始位置和速度，一个物体从给定的初始位置触发，在给定的速度下进行移动，遇到矩阵的边
        缘则发生镜面反射。
        无论物体经过 0 还是 1, 都不影响其速度
        请计算并给出经过 t 时间单位后，物体经过 1 点的次数
        矩阵以左上角位置为[0,0](列(x),行(行)),例如下面A点坐标为[2,1](第二列，第一行)

+--------------------------- 递增(x)
        | 0 0 1 0 0 0 0 1 0 0 0 0
        | 0 0 1 0 0 0 0 1 0 0 0 0
        | 0 0 1 0 0 0 0 1 0 0 0 0
        | 0 0 1 0 0 0 0 1 0 0 0 0
        | 0 0 1 0 0 0 0 1 0 0 0 0
        | 0 0 1 0 0 0 0 1 0 0 0 0
        | 0 0 1 0 0 0 0 1 0 0 0 0
        |
        递增(y)
        法意

        ·如果初始位置的点是 1, 也计算在内· 时间的最小单位为1，不考虑小于 1 个时间单位内经过的点

        输入描述第一行为初始信息

        第二行开始一共 h 行，为二维矩阵信息其中：
        · w, h 为矩阵的宽和高
        · x, y 为起始位置
        · sx, sy 为初始速度· t为经过的时间

        所有输入都是有效的，数据范围如下：
        $\cdot0<w<100$
        $\cdot0<h<100$ $\cdot0\leq x<w$ $\cdot0\leq y<h$ $\cdot \cdot 1\leq $sx$\leq 1$ $\cdot\cdot1\leq sy\leq1$ $\cdot0\leq t<100$
        输出描述

        经过 1 的个数
        注意初始位置也要计算在内
*/


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 创建Scanner对象用于接收控制台输入
        Scanner scanner = new Scanner(System.in);
        // 读取矩阵的宽度w、高度h、物体初始位置x、y、物体移动速度分量sx、sy以及总时间t
        int w = scanner.nextInt(); // 矩阵宽度
        int h = scanner.nextInt(); // 矩阵高度
        int x = scanner.nextInt(); // 物体初始x坐标
        int y = scanner.nextInt(); // 物体初始y坐标
        int sx = scanner.nextInt(); // 物体在x轴方向的速度分量
        int sy = scanner.nextInt(); // 物体在y轴方向的速度分量
        int t = scanner.nextInt(); // 总移动时间
        scanner.nextLine(); // 读取并丢弃当前行剩余的所有数据，包括换行符

        // 根据输入的高度和宽度初始化矩阵
        int[][] matrix = new int[h][w];
        for (int i = 0; i < h; i++) {
            // 读取矩阵的每一行，每行是一个由'0'和'1'组成的字符串
            String line = scanner.nextLine();
            for (int j = 0; j < w; j++) {
                // 将每个字符转换为整数0或1，并存入矩阵对应位置
                matrix[i][j] = line.charAt(j) - '0';
            }
        }
        // 关闭scanner对象
        scanner.close();

        // 调用countOnes方法计算并输出物体在给定时间内经过的1的数量
        System.out.println(countOnes(w, h, x, y, sx, sy, t, matrix));
    }

    // countOnes方法用于计算物体在移动过程中经过的1的数量
    public static int countOnes(int w, int h, int x, int y, int sx, int sy, int t, int[][] matrix) {
        // 从物体的初始位置开始计数，如果初始位置是1，则计数器初始值为1
        int count = matrix[y][x];
        // 循环t次，每次代表物体移动一个时间单位
        while (t-- > 0) {
            // 根据速度分量更新物体位置
            x += sx;
            y += sy;
            // 如果物体撞到左右边界，反转x轴方向速度分量
            if (x == 0 || x == w - 1) sx = -sx;
            // 如果物体撞到上下边界，反转y轴方向速度分量
            if (y == 0 || y == h - 1) sy = -sy;
            // 每次移动后，如果新位置是1，则累加到计数器
            count += matrix[y][x];
        }
        // 返回计数器的值，即物体经过的1的总数
        return count;
    }
}
