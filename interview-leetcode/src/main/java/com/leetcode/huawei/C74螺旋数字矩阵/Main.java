package com.leetcode.huawei.C74螺旋数字矩阵;
/*题目描述疫情期间，小明隔离在家，百无聊赖，在纸上写数字玩。他发明了一种写法：
        给出数字个数n和行数m $(0<n\leq999,0<m\leq999)$ ,从左上角的1开始，按照顺时针螺旋向内写方式，依次写出2，3...n,最终形成一
        个m行矩阵。
        小明对这个矩阵有些要求：
        ·每行数字的个数一样多· 列的数量尽可能少

        · 填充数字时优先填充外部
        ·数字不够时，使用单个$^{*}$号占位

        输入描述输入一行，两个整数，空格隔开，依次表示n、m
        输出描述
        符合要求的唯一矩阵
        用例1
        输入：
        9 4
        输出：
        1 2 3
        * * 4
        9 * 5
        8 7 6

        说明：
        9个数字写成4行，最少需要3列*/
import java.util.Scanner;

/*通过模拟螺旋填充的过程来解决。首先，我们需要确定矩阵的列数，然后创建一个m行n列的矩阵，初始值都为"*"。然后，我们按照顺时针螺旋的方式填充数字，直到填充完所有的数字。

        以下是实现的伪代码：

        计算列数：列数等于数字个数n除以行数m向上取整。
        创建一个m行n列的矩阵，初始值都为"*"。
        初始化当前填充的数字为1。
        按照顺时针螺旋的方式填充数字：
        从左到右填充上边，
        从上到下填充右边，
        从右到左填充下边，
        从下到上填充左边。
        在填充的过程中，
        如果遇到已经填充过的位置或者超出矩阵的范围，
        那么改变方向。
        如果当前填充的数字大于n，那么停止填充。
        输出矩阵。*/
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int cols = (n + m - 1) / m;  // 计算列数, 向上取整
        String[][] matrix = new String[m][cols];
        for (int i = 0; i < m; i++) {  // 初始化矩阵
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = "*";
            }
        }
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 定义方向数组 顺时针
        int row = 0, col = 0, dirIndex = 0; // 初始化行、列、方向索引
        for (int i = 1; i <= n; i++) {
            matrix[row][col] = String.valueOf(i);
            int nextRow = row + directions[dirIndex][0], nextCol = col + directions[dirIndex][1];
            if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= cols || !matrix[nextRow][nextCol].equals("*")) {
                dirIndex = (dirIndex + 1) % 4; // 螺旋方向改变
            }
            row += directions[dirIndex][0];
            col += directions[dirIndex][1];
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
