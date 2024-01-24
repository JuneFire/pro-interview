package com.leetcode.huawei.low;

// 找座位， 空座 左右两边必须为空座，当前空座才是有效座位
public class zzw_shuzi {

    public static void main(String[] args) {
        // 示例输入：10001
        int[] seats = {0, 0, 1, 0, 0, 0, 1};
        int result = maxAdditionalAudience(seats);
        System.out.println("最多还能坐下观众数：" + result);
    }

    public static int maxAdditionalAudience(int[] seats) {
        int maxAdditional = 0;
        int currentGap = 0;

        for (int seat : seats) {
            if (seat == 0) {
                // 如果当前座位为空，则增加当前的间隔
                currentGap++;
            } else {
                // 如果当前座位已经坐人，计算当前间隔中能够坐观众的数量
                maxAdditional += (currentGap - 1) / 2;
                currentGap = 0;
            }
        }

        // 处理最后一段间隔
        maxAdditional += currentGap / 2;

        return maxAdditional;
    }
}
