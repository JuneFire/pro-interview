package com.leetcode.huawei.C219结队编程;
/*题目描述某部门计划通过结队编程来进行项目开发，已知该部门有 N 名员工，每个员工有独一无二的职级，每三个员工形成一个小组进行结队编
        程，结队分组规则如下：
        从部门中选出序号分别为i、j、k的3名员工，他们的职级分贝为 level[i], level[i], level[k],
        结队小组满足 level[i]< level[i]< level[k] 或者 level[i]> level[i]> level[k],
        其中$0\leq i<j<k<n$。
        请你按上述条件计算可能组合的小组数量。同一员工可以参加多个小组。
        输入描述
        第一行输入：员工总数 n
        第二行输入：按序号依次排列的员工的职级 level, 中间用空格隔开
        备注：
        $\cdot1\leq n\leq6000$
        $\cdot1\leq\operatorname*{level}[i]\leq10^n5$
        输出描述
        可能结队的小组数量
        样例输入
        输入
        4
        1 2 3 4
        输出4
        说明
        可能结队成的组合(1,2,3)、(1,2,4)、(1,3,4)、(2,3,4)
        输入
        3
        5 4 7
        输出 0
        说明
        根据结队条件，我们无法为该部门组建小组*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine()); //  员工总数
        int[] levels = new int[n]; //  每个员工的职级
        String[] inputLevels = sc.nextLine().split(" "); //  每个员工职级的字符串数组
        for (int i = 0; i < n; i++) { // 遍历字符串数组
            levels[i] = Integer.parseInt(inputLevels[i]); // 将每个员工的职级字符串转换为整数，并存储到levels数组中
        }

        System.out.println(countTeams(n, levels)); // 调用countTeams方法计算并输出可以结成的队伍数量
    }

    public static long countTeams(int n, int[] levels) {
        long ans = 0; // 初始化答案变量，用于存储可能的队伍数量
        int[] smallerToLeft = new int[n]; // 创建数组，存储每个员工左侧比他职级小的员工数量
        int[] greaterToLeft = new int[n]; // 创建数组，存储每个员工左侧比他职级大的员工数量
        int[] smallerToRight = new int[n]; // 创建数组，存储每个员工右侧比他职级小的员工数量
        int[] greaterToRight = new int[n]; // 创建数组，存储每个员工右侧比他职级大的员工数量

        // 预计算每个员工左侧的职级比较情况
        for (int i = 1; i < n; i++) { // 从第二个员工开始遍历
            for (int j = 0; j < i; j++) { // 遍历当前员工左侧的所有员工
                if (levels[j] < levels[i]) { // 如果左侧员工职级小于当前员工
                    smallerToLeft[i]++; // 对应的数量加一
                } else if (levels[j] > levels[i]) { // 如果左侧员工职级大于当前员工
                    greaterToLeft[i]++; // 对应的数量加一
                }
            }
        }

        // 预计算每个员工右侧的职级比较情况
        for (int i = n - 2; i >= 0; i--) { // 从倒数第二个员工开始向前遍历
            for (int j = n - 1; j > i; j--) { // 遍历当前员工右侧的所有员工
                if (levels[j] < levels[i]) { // 如果右侧员工职级小于当前员工
                    smallerToRight[i]++; // 对应的数量加一
                } else if (levels[j] > levels[i]) { // 如果右侧员工职级大于当前员工
                    greaterToRight[i]++; // 对应的数量加一
                }
            }
        }

        // 计算可能的队伍数量
        for (int i = 0; i < n; i++) { // 遍历每个员工
            // 将当前员工左侧小于他的员工数量与右侧大于他的员工数量相乘，并加到答案中
            // 将当前员工左侧大于他的员工数量与右侧小于他的员工数量相乘，并加到答案中
            ans += (long) smallerToLeft[i] * greaterToRight[i] + (long) greaterToLeft[i] * smallerToRight[i];
        }

        return ans; // 返回计算出的队伍数量
    }
}

