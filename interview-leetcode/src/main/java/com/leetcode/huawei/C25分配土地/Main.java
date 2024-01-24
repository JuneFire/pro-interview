package com.leetcode.huawei.C25分配土地;
/*        从前有个村庄，村民们喜欢在各种田地上插上小旗子，旗子上标识了各种不同的数字。
    某天集体村民决定将覆盖相同数字的最小矩阵形的土地分配给村里做出巨大贡献的村民，请问此次分配土地，做出贡献的村民种最大会分
    配多大面积？
    输入描述第一行输入m 和 n,

    · m 代表村子的土地的长
    · n代表土地的宽
    第二行开始输入地图上的具体标识
    输出描述
    此次分配土地，做出贡献的村民种最大会分配多大面积
    备注
    旗子上的数字为1~500, 土地边长不超过500
    未插旗子的土地用0标识*/

import java.util.*;

//这个问题的关键在于找到所有相同数字的旗子，并确定它们的最小和最大的横纵坐标，然后根据这些坐标计算出覆盖所有这些旗子的最小矩阵的面积
public class Main {
    static int m, n;
    static int[][] grid;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }
        int maxArea = 0;
        for (int flag = 1; flag <= 500; flag++) {
            int minX = m, minY = n, maxX = -1, maxY = -1;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == flag) {
                        minX = Math.min(minX, i);  //最小的X坐标
                        minY = Math.min(minY, j);  //最小的Y坐标
                        maxX = Math.max(maxX, i);  //最大的X坐标
                        maxY = Math.max(maxY, j);  //最大的Y坐标
                    }
                }
            }
            if (maxX != -1) {
                maxArea = Math.max(maxArea, (maxX - minX + 1) * (maxY - minY + 1));  //不同的flag下的面积取最大值
            }
        }
        System.out.println(maxArea);
    }
}
