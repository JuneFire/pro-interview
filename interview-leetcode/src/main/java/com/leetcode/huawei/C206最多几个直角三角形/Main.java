package com.leetcode.huawei.C206最多几个直角三角形;
/*题目描述有N条线段，长度分别为a[1]-a[n]。
        现要求你计算这N条线段最多可以组合成几个直角三角形。
        每条线段只能使用一次，每个三角形包含三条线段。
        输入描述
        第一行输入一个正整数T (1<=T<=100) , 表示有T组测试数据.
        对于每组测试数据，接下来有T行，
        每行第一个正整数N，表示线段个数(3<=N<=20),接着是N个正整数，表示每条线段长度，(0<a[i]<100)。
        输出描述
        对于每组测试数据输出一行，每行包括一个整数，表示最多能组合的直角三角形个数
        用例
        输入
        1
        7 3 4 5 6 5 12 13
        说明
        可以组成2个直角三角形 (3,4,5)、(5,12,13)*/
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    /**
     * 读取测试数据的数量T。
     * 对于每组测试数据，首先读取线段的数量N，然后读取N条线段的长度。
     * 对线段的长度进行排序。
     * 遍历排序后的线段，对于每三条线段，如果它们可以组成直角三角形（即最长的线段的平方等于其他两条线段的平方和），则计数器加一。
     * 输出计数器的值
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] lengths = new int[N];
            for (int i = 0; i < N; i++) {
                lengths[i] = sc.nextInt();
            }
            Arrays.sort(lengths);
            List<List<Integer>> res = new ArrayList<>();
//            int count = 0;
            for (int i = 0; i < N - 2; i++) {
                for (int j = i + 1; j < N - 1; j++) {
                    for (int k = j + 1; k < N; k++) {
                        if (lengths[i] * lengths[i] + lengths[j] * lengths[j] == lengths[k] * lengths[k]) {
                            res.add(Arrays.asList(lengths[i], lengths[j], lengths[k]));
                        }
                    }
                }
            }
            // res 去重
//            res.stream().filter()
            Set<List<Integer>> set = new HashSet<>(res);
            List<List<Integer>> distinctList = new ArrayList<>(set);

            distinctList.forEach(System.out::println); // 输出去重后的数组


        }
    }

}



/*

import java.util.Arrays;
        import java.util.Scanner;
        import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // 创建Scanner对象以读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 读取测试数据组数
        int testCases = scanner.nextInt();
        // 创建一个二维数组来存储每组测试数据
        int[][] inputData = new int[testCases][];

        // 读取每组测试数据
        for (int i = 0; i < testCases; i++) {
            // 读取线段数量
            int segments = scanner.nextInt();
            // 创建一个数组来存储线段长度
            int[] lengths = new int[segments];
            // 读取每条线段的长度
            for (int j = 0; j < segments; j++) {
                lengths[j] = scanner.nextInt();
            }
            // 将线段长度数组存入inputData数组
            inputData[i] = lengths;
        }

        // 遍历每组测试数据
        for (int[] lengths : inputData) {
            // 对线段长度进行升序排序
            Arrays.sort(lengths);
            // 调用nonRecursiveDFS函数并输出结果
            System.out.println(nonRecursiveDFS(lengths));
        }
    }

    public static int nonRecursiveDFS(int[] lengths) {
        int maxTriangles = 0;
        boolean[] usedSegments = new boolean[lengths.length];
        Stack<State> stack = new Stack<>();
        stack.push(new State(0, 0, usedSegments));

        // 遍历状态栈
        while (!stack.isEmpty()) {
            State currentState = stack.pop();
            int currentIndex = currentState.index;
            int currentCount = currentState.count;
            usedSegments = currentState.used.clone();

            maxTriangles = Math.max(maxTriangles, currentCount);

            // 遍历线段数组，从currentIndex开始
            for (int i = currentIndex; i < lengths.length; i++) {
                if (usedSegments[i]) continue;
                for (int j = i + 1; j < lengths.length; j++) {
                    if (usedSegments[j]) continue;
                    for (int k = j + 1; k < lengths.length; k++) {
                        if (usedSegments[k]) continue;

                        // 检查当前三条线段是否满足勾股定理
                        if (lengths[i] * lengths[i] + lengths[j] * lengths[j] == lengths[k] * lengths[k]) {
                            boolean[] newUsedSegments = usedSegments.clone();
                            newUsedSegments[i] = true;
                            newUsedSegments[j] = true;
                            newUsedSegments[k] = true;
                            stack.push(new State(i + 1, currentCount + 1, newUsedSegments));
                        }
                    }
                }
            }
        }

        return maxTriangles;
    }

    // State类用于存储每个状态的信息，包括当前索引、已找到的直角三角形数量和已使用的线段
    static class State {
        int index;
        int count;
        boolean[] used;

        State(int index, int count, boolean[] used) {
            this.index = index;
            this.count = count;
            this.used = used;
        }
    }
}

*/
