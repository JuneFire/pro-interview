package com.leetcode.huawei.C220高效货运;
/*
题目描述老李是货运公司承运人，老李的货车额定载货重量为 wt。
        现有两种货物：

        ·货物 A 单件重量为 wa, 单件运费利润为 pa ·货物 B 单件重量为 wb, 单件运费利润为 pb
        老李每次发车时载货总重量刚好为货车额定的载货重量 wt, 车上必须同时有货物 A 和货物 B , 货物A、B不可切割。老李单次满载运输可获得的最高利润是多少？
        输入描述
        ·第一列输入为货物 A 的单件重量 wa, $0<wa<10000$
        ·第二列输入为货物 B  的单件重量wb，$0<wb<10000$
        ·第三列输入为货车的额定载重wt，$0< \mathrm{wt< 100000}$
        · 第四列输入为货物 A 的单件运费利润pa，$0<pa<1000$
        · 第五列输入为货物 B  的单件运费利润pb，$0< \mathrm{pb< 1000}$
        输出描述单次满载运输的最高利润
        用例1
        输入
        1 10 8 36 15 7
        输出
        44
*/

// 这个问题可以使用贪心算法来解决。我们可以先计算出货物A和货物B的单位重量利润，
// 然后优先装载单位重量利润更高的货物，直到无法再装载为止。
// 然后再尝试装载单位重量利润较低的货物，直到货车装满为止
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // 使用Stream API将输入的字符串按空格分割，并转换为整数数组
        int[] values = Stream.of(input.split(" ")) // 将输入的字符串分割成字符串数组
                .mapToInt(Integer::parseInt) // 将字符串数组的每个元素转换为整数
                .toArray(); // 将流转换为数组

        // 从数组中提取各个变量的值
        int wa = values[0]; // 货物A的单件重量
        int wb = values[1]; // 货物B的单件重量
        int wt = values[2]; // 货车的额定载重
        int pa = values[3]; // 货物A的单件运费利润
        int pb = values[4]; // 货物B的单件运费利润

        // 调用calculateMaxProfit方法计算最高利润
        int maxProfit = calculateMaxProfit(wa, wb, wt, pa, pb);
        // 输出最高利润
        System.out.println(maxProfit);
    }

    // 定义calculateMaxProfit方法，计算最高利润
    public static int calculateMaxProfit(int wa, int wb, int wt, int pa, int pb) {
        int maxProfit = 0; // 初始化最高利润为0

        // 遍历可能的货物A数量，确保货物A和B的总重量不超过额定载重
        for (int countA = 1; countA * wa < wt; countA++) {
            // 计算除去货物A后剩余的重量
            int remainingWeight = wt - countA * wa;
            // 如果剩余重量可以被货物B的单件重量整除，说明可以装满货物B
            if (remainingWeight % wb == 0) {
                // 计算货物B的数量
                int countB = remainingWeight / wb;
                // 计算当前组合的利润
                int profit = countA * pa + countB * pb;
                // 如果当前组合的利润大于之前的最高利润，则更新最高利润
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        // 返回最高利润
        return maxProfit;
    }
}

