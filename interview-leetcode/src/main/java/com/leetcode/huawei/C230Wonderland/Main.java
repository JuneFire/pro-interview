package com.leetcode.huawei.C230Wonderland;

/*
题目描述Wonderland是小王居住地一家很受欢迎的游乐园。
        Wonderland目前有4种售票方式，分别为
        ·-日票(1天)、
        ·三日票(3天)、 ·周票(7天)

        ·月票(30天)。
        每种售票方式的价格由一个数组给出，每种票据在票面时限内可以无限制地进行游玩。例如：
        小王在第10日买了一张三日票，小王可以在第10日、第11日和第12日进行无限制地游玩。

        小王计划在接下来一年多次游玩该游乐园。小王计划地游玩日期将由一个数组给出。现在，请您根据给出地售票价格数组和小王计划游玩日期数组，返回游玩计划所需要地最低消费。

        输入描述
        输入为2个数组：

        ·售票价格数组为costs，costs.length =4, 默认顺序为一日票、三日票、周票和月票。·小王计划游玩日期数组为days，$1\leq $days.length $\leq 365, \quad 1\leq \mathrm{days[ i] }\leq 365$, 默认顺序为升序。

        输出描述完成游玩计划的最低消费。
        用例
        输入
        5 14 30 100
        13520 21 200 202 230
       输出 40*/
/*
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 创建Scanner对象用于获取用户输入
        Scanner scanner = new Scanner(System.in);

        // 从用户输入中获取票价，输入格式为以空格分隔的四个整数
        int[] costs = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 从用户输入中获取游玩日期，输入格式为以空格分隔的整数
        int[] days = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 关闭Scanner对象
        scanner.close();

        // 调用mincostTickets方法计算最低消费，并打印结果
        System.out.println(mincostTickets(costs, days));
    }

    public static int mincostTickets(int[] costs, int[] days) {
        // 找到days数组中的最大值，确定旅游的最后一天
        int maxDay = Arrays.stream(days).max().getAsInt();
        // 创建一个长度为maxDay+1的布尔数组，用于标记每一天是否需要游玩
        boolean[] travelDays = new boolean[maxDay + 1];
        for (int day : days) {
            travelDays[day] = true;
        }

        // 创建一个长度为maxDay+1的整数数组，用于保存每一天的最低消费
        int[] dp = new int[maxDay + 1];
        for (int i = 1; i <= maxDay; i++) {
            // 如果这一天不需要游玩，那么这一天的最低消费就和前一天的最低消费相同
            if (!travelDays[i]) {
                dp[i] = dp[i - 1];
                continue;
            }

            // 计算购买各种票后的消费
            int cost1 = dp[Math.max(0, i - 1)] + costs[0];  // 购买1天的票
            int cost3 = dp[Math.max(0, i - 3)] + costs[1];  // 购买3天的票
            int cost7 = dp[Math.max(0, i - 7)] + costs[2];  // 购买7天的票
            int cost30 = dp[Math.max(0, i - 30)] + costs[3];  // 购买30天的票

            // 这一天的最低消费就是购买各种票后消费的最小值
            dp[i] = Math.min(Math.min(cost1, cost3), Math.min(cost7, cost30));
        }

        // 返回最后一天的最低消费，即为完成整个游玩计划的最低消费
        return dp[maxDay];
    }
}
*/
public class Main {
    public static void main(String[] args) {
        int[] costs = {5, 14, 30, 100};
        int[] days = {1, 3, 5, 20, 21, 200, 202, 230};
        System.out.println(mincostTickets(costs, days));
    }
    // 我们可以定义一个长度为366的数组dp，其中dp[i]表示在第i天结束时的最小花费。然后我们可以遍历每一天，对于游玩的日子，我们需要考虑购买哪种票是最优的，对于不游玩的日子，花费和前一天是一样的。
    public static int mincostTickets(int[] costs, int[] days) {
        int n = days.length;
        int[] dp = new int[366];
        int[] durations = {1, 3, 7, 30};
        boolean[] isTravelDay = new boolean[366];
        for (int day : days) {
            isTravelDay[day] = true;
        }
        for (int i = 1; i <= 365; i++) {
            if (!isTravelDay[i]) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < 4; j++) {
                int preDay = i - durations[j] >= 0 ? i - durations[j] : 0;
                dp[i] = Math.min(dp[i], dp[preDay] + costs[j]);
            }
        }
        return dp[365];
    }
}
