package com.leetcode.huawei.C58围棋的气;
/*围棋棋盘由纵横各19条线垂直相交组成，棋盘上一共19x 19=361 个交点，对弈双方一方执白棋，一方执黑棋，落子时只能将棋子置于交点上。
        “气”是围棋中很重要的一个概念，某个棋子有几口气，是指其上下左右方向四个相邻的交叉点中，有几个交叉点没有棋子，由此可知：
        1.在棋盘的边缘上的棋子最多有 3 口气 (黑1), 在棋盘角点的棋子最多有2口气 (黑2), 其他情况最多有4口气 (白1)
        2.所有同色棋子的气之和叫做该色棋子的气，需要注意的是，同色棋子重合的气点，对于该颜色棋子来说，只能计算一次气，比如下图

        中，黑棋一共4口气，而不是5口气，因为黑1和黑2中间红色三角标出来的气是两个黑棋共有的，对于黑棋整体来说只能算一个气。
        输入描述输入包含两行数据，
        每行数据以空格分隔，数据个数是2的整数倍，每两个数是一组，代表棋子在棋盘上的坐标；
        坐标的原点在棋盘左上角点，第一个值是行号，范围从0到18；第二个值是列号，范围从0到18。
        举例说明：如：
        0 5 8 9 9 10
        5 0 9 9 9 8
        第一行数据表示三个坐标 (0,5)、(8,9)、(9,10) 第一行表示黑棋的坐标，第二行表示白棋的坐标。题目保证输入两行数据」无空行且每行按前文要求是偶数个，每个坐标不会超出棋盘范围。

        输出描述
        两个数字以空格分隔，第一个数代表黑棋的气数，第二个数代表白棋的气数。
        8 7*/
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[19][19];
        boolean[][] countedBlack = new boolean[19][19];
        boolean[][] countedWhite = new boolean[19][19];
        String[] black = sc.nextLine().split(" ");
        String[] white = sc.nextLine().split(" ");
        for (int i = 0; i < black.length; i += 2) {
            int dx = Integer.parseInt(black[i]);
            int dy = Integer.parseInt(black[i + 1]);
            board[dx][dy] = 1;  // 白棋位置
        }

        for (int i = 0; i < white.length; i += 2) {
            board[Integer.parseInt(white[i])][Integer.parseInt(white[i + 1])] = 2; // 黑棋位置
        }


        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int blackQi = 0, whiteQi = 0;
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (board[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k], ny = j + dy[k];
                        if (nx >= 0 && nx < 19 && ny >= 0 && ny < 19 && board[nx][ny] == 0) {
                            if (board[i][j] == 1 && !countedBlack[nx][ny]) {
                                blackQi++;
                                countedBlack[nx][ny] = true;
                            } else if (board[i][j] == 2 && !countedWhite[nx][ny]) {
                                whiteQi++;
                                countedWhite[nx][ny] = true;
                            }

                        }
                    }
                }
            }
        }
        System.out.println(blackQi + " " + whiteQi);
    }
}
