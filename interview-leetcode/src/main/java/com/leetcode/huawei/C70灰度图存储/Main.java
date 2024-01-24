package com.leetcode.huawei.C70灰度图存储;
/*题目描述黑白图像常采用灰度图的方式存储，即图像的每个像素填充一个灰色阶段值，256节阶灰图是一个灰阶值取值范围为0-255的灰阶矩阵，0
        表示全黑，255表示全白，范围内的其他值表示不同的灰度。
        但在计算机中实际存储时，会使用压缩算法，其中一个种压缩格式描述如如下：
        10 10 255 34 0 1 255 80 3 255 6 0 5 255 4 0 7 255 2 0 9 255 21
        1.所有的数值以空格分隔；
        2. 前两个数分别表示矩阵的行数和列数；
        3.从第三个数开始，每两个数一组，每组第一个数是灰阶值，第二个数表示该灰阶值从左到右，从上到下(可理解为二维数组按行存储

        在一维矩阵中) 的连续像素个数。比如题目所述的例子，“255 34”表示有连续 34 个像素的灰阶值是 255。
        4.如下图所：连续34个255，1个0 再来连续8个255。
        如此，图像软件在打开此格式灰度图的时候，就可以根据此算法从压缩数据恢复出原始灰度图矩阵。
        请从输入的压缩数恢复灰度图原始矩阵，并返回指定像素的灰阶值。
        输入描述
        输入包行两行，第一行是灰度图压缩数据，第二行表示一个像素位置的行号和列号，如 0 0 表示左上角像素。
        备注：
        1、系保证输入的压缩数据是合法有效的，不会出现数据起界、数值不合法等无法恢复的场景；
        2、系统保证输入的像素坐标是合法的，不会出现不在矩阵中的像素；
        3、矩阵的行和列数范图为：(0,100];
        4、灰阶值取值范图：[0,255];

         10 10 56 34 99 1 87 8 99 3 255 6 99 5 255 4 99 7 255 2 99 9 255 21
         3 4

        输出描述 输出数据表示的灰阶矩阵的指定像素的灰阶值。

        0 // 结合上面的图，第三行4列的值为0*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];
        int count = 0;
        while (count < rows * cols) {
            int grayScale = scanner.nextInt();
            int pixelCount = scanner.nextInt();
            for (int i = 0; i < pixelCount; i++) {
                matrix[count / cols][count % cols] = grayScale;
                count++;
            }
        }
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.println(matrix[x][y]);
    }
}
