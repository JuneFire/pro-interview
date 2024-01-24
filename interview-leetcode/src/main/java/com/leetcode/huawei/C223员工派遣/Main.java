package com.leetcode.huawei.C223员工派遣;
/*题目描述某公司部门需要派遣员工去国外做项目。
        现在，代号为x 的国家和代号为 y 的国家分别需要 cntx 名和 cnty 名员工。
        部门每个员工有一个员工号 (1,2,3,...),工号连续，从1开始。
        部长派遣员工的规则：

        ·规则1：从[1,k]中选择员工派遣出去·规则2：编号为 x 的倍数的员工不能去 x 国，编号为 y 的倍数的员工不能去 y 国。

        问题： 找到最小的 k, 使得可以将编号在[1,k] 中的员工分配给 x 国和 y 国，且满足 x 国和 y 国的需求。
        输入描述
        四个整数 x, y, cntx, cnty。
        $\cdot2\leq x<y\leq30000$
        ·x和y一定是质数. $1\leq $cntx$, $cnty$< 10^{n}9$
        · cnt$x+ $cnty$\leq 10^ng$
        输出描述满足条件的最小的k*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int cntx = scanner.nextInt();
        int cnty = scanner.nextInt();
        System.out.println(minK(x, y, cntx, cnty));
    }

    public static int minK(int x, int y, int cntx, int cnty) {
        int k = 1;
        while (cntx > 0 || cnty > 0) {
            if (k % x != 0 && cntx > 0) {
                cntx--;
            } else if (k % y != 0 && cnty > 0) {
                cnty--;
            }
            k++;
        }
        return k - 1;
    }
}
/*

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x, y, cntX, cntY; // 定义静态变量x, y, cntX, cntY

        x = sc.nextLong(); // 读取国家X的倍数限制
        y = sc.nextLong(); // 读取国家Y的倍数限制
        cntX = sc.nextLong(); // 读取国家X需要的员工数量
        cntY = sc.nextLong(); // 读取国家Y需要的员工数量

        long minID = cntX + cntY; // 设置员工ID的最小值，初值为两国需要的员工总数
        long maxID = 1000000000L; // 设置员工ID的最大值

        // 通过二分查找算法找到满足条件的最小员工ID
        while (minID <= maxID) {
            long midID = minID + (maxID - minID) / 2; // 计算中间值midID

            long excludedX = midID / x; // 计算在[1, midID]范围内不能去X国的员工数
            long excludedY = midID / y; // 计算在[1, midID]范围内不能去Y国的员工数
            long excludedBoth = midID / (x * y); // 计算在[1, midID]范围内同时不能去X国和Y国的员工数

            long neededX = Math.max(0, cntX - (excludedY - excludedBoth)); // 计算X国实际需要的员工数
            long neededY = Math.max(0, cntY - (excludedX - excludedBoth)); // 计算Y国实际需要的员工数
            long totalExcluded = midID - excludedX - excludedY + excludedBoth; // 计算总共不能使用的员工数

            // 判断当前midID是否满足条件
            if (neededX + neededY <= totalExcluded) {
                maxID = midID - 1; // 如果满足条件，降低最大ID的搜索范围
            } else {
                minID = midID + 1; // 如果不满足条件，提高最小ID的搜索范围
            }
        }

        System.out.println(minID); // 输出满足条件的最小员工ID
        sc.close(); // 关闭扫描器
    }
}
*/
