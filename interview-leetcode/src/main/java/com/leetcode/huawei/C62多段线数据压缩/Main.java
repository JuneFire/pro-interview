package com.leetcode.huawei.C62多段线数据压缩;
/*
题目描述下图中，每个方块代表一个像素，每个像素用其行号和列号表示。为简化处理，多段线的走向只能是水平、竖直、斜向45度。

        上图中的多段线可以用下面的坐标串表示：(2,8),(3,7),(3,6),(3,5),(4,4),(5,3),(6,2),(7,3),(8,4),(7,5)。
        但可以发现，这种表示不是最简的，其实只需要存储6个蓝色的关键点即可，它们是线段的起点、拐点、终点，而剩下4个点是冗余的。

        即可以简化为：(2,8)、(3,7)、(3,5)、(6,2)、(8,4)、(7,5)
        现在，请根据输入的包含有冗余数据的多段线坐标列表，输出其最简化的结果。输入描述
        2 8 3 7 3 6 3 5 4 4 5 3 6 2 7 3 8 4 7 5
        1、所有数字以空格分隔，每两个数字一组，第一个数字是行号，第二个数字是列号；
        2、行号和列号范围为[0,64),用例输入保证不会越界，考生不必检查；
        3、输入数据至少包含两个坐标点。
        输出描述
        2 8 3 7 3 5 6 2 8 4 7 5
        压缩后的最简化坐标列表，和输入数据的格式相同。
        备注：输出的坐标相对顺序不能变化。
*/
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] inputs = sc.nextLine().split(" ");
        // 输入数据至少包含两个坐标点
        if (inputs.length < 4) {
            return;
        }
        List<String> points = new ArrayList<>(Arrays.asList(inputs));
        List<String> result = new ArrayList<>();
        for (int i = 0; i < points.size(); i += 2) {
            if (i == 0 || i == points.size() - 2 || !isOnSameLine(points.get(i) + points.get(i + 1), points.get(i + 2) + points.get(i + 3), points.get(i + 4) + points.get(i + 5))) {
                result.add(points.get(i) + points.get(i + 1));
            }
        }
        for (String point : result) {
            System.out.print(point.charAt(0) + " " + point.charAt(1) + " ");
        }
    }
    // 用于检查三个点是否在同一条直线上。如果三个点在同一条直线上，那么它们的斜率应该相等。这个方法通过计算两两点之间的斜率，并比较这些斜率是否相等，来判断三个点是否在同一条直线上
    private static boolean isOnSameLine(String point1, String point2, String point3) {
        int x1 = Integer.parseInt(point1.substring(0, 1));
        int y1 = Integer.parseInt(point1.substring(1, 2));
        int x2 = Integer.parseInt(point2.substring(0, 1));
        int y2 = Integer.parseInt(point2.substring(1, 2));
        int x3 = Integer.parseInt(point3.substring(0, 1));
        int y3 = Integer.parseInt(point3.substring(1, 2));
        return (y2 - y1) * (x3 - x2) == (y3 - y2) * (x2 - x1);
    }
}
