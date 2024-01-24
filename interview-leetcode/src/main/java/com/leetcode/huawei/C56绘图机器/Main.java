package com.leetcode.huawei.C56绘图机器;
/*绘图机器的绘图笔初始位置在原点(0,0)机器启动后按照以下规则来进行绘制直线。
        1.尝试沿着横线坐标正向绘制直线直到给定的终点E
        2.期间可以通过指令在纵坐标轴方向进行偏移，offsetY为正数表示正向偏移，为负数表示负向偏移
        给定的横坐标终点值E 以及若干条绘制指令，
        请计算绘制的直线和横坐标轴以及x=E的直线组成的图形面积。
        输入描述
        · 首行为两个整数 N 和 E
        ·表示有N条指令，机器运行的横坐标终点值E
        ·接下来N行 每行两个整数表示一条绘制指令x offsetY
        · 用例保证横坐标x以递增排序的方式出现
        · 且不会出现相同横坐标x
        输出描述
        一个整数表示计算得到的面积 用例保证结果范围在0到4294967295之内。

用例：输入
        2 4
        0 1
        2 -2
     输出
        4*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 输入指令的数量
        int e = scanner.nextInt(); // 终点横坐标

        if (e == 0) { // 如果终点横坐标为0
            System.out.println(0); // 输出面积为0
            return;
        }

        int[] offsets = new int[e]; // 创建一个长度为终点横坐标的整数数组，用于存储纵坐标偏移量

        for (int i = 0; i < n; i++) {
            int cur_x = scanner.nextInt(); // 当前点的横坐标
            int offset_y = scanner.nextInt(); // 当前点纵坐标相较于上一个点纵坐标的偏移量
            offsets[cur_x] = offset_y; // 将偏移量存储在对应横坐标位置上
        }

        int[] dp = new int[e]; // 创建一个长度为终点横坐标的整数数组，用于存储每个横坐标位置的纵坐标偏移量之和
        dp[0] = offsets[0]; // 第一个位置的纵坐标偏移量为指令中的纵坐标偏移量
        for (int i = 1; i < e; i++) { // 从第二个位置开始遍历
            dp[i] = offsets[i] + dp[i - 1]; // 当前位置的纵坐标偏移量为指令中的纵坐标偏移量加上前一个位置的纵坐标偏移量之和
        }

        int ans = 0; // 初始化面积为0
        for (int num : dp) { // 遍历每个横坐标位置的纵坐标偏移量之和
            ans += Math.abs(num); // 将绝对值加到面积中
        }
        System.out.println(ans); // 输出面积

        scanner.close(); // 关闭输入流
    }
}

