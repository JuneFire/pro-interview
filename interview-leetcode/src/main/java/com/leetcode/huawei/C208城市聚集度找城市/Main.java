package com.leetcode.huawei.C208城市聚集度找城市;
/*题目描述一张地图上有n个城市，城市和城市之间有且只有一条道路相连：要么直接相连，要么通过其它城市中转相连 (可中转一次或多次) 。城
        市与城市之间的道路都不会成环。
        当切断通往某个城市 i 的所有道路后，地图上将分为多个连通的城市群，设该城市i的聚集度为DPi(Degree of Polymerization)，
        DPi=max(城市群1的城市个数，城市群2的城市个数，...城市群m 的城市个数)。
        请找出地图上DP值最小的城市 (即找到城市j，使得DPj= min(DP1,DP2...DPn))
        提示：如果有多个城市都满足条件，这些城市都要找出来 (可能存在多个解)
        提示：DPi的计算，可以理解为已知一棵树，删除某个节点后；生成的多个子树，求解多个子数节点数的问题。
        输入描述
        每个样例：第一行有一个整数N，表示有N个节点。1<=N<= 1000。
        接下来的N-1行每行有两个整数x，y, 表示城市x与城市y连接。1<=x,y<=N
        输出描述
        输出城市的编号。如果有多个，按照编号升序输出。*/

/**
 *读取城市的数量N和城市之间的连接。
 * 构建一个图来表示城市之间的连接。
 * 对于每个城市，计算切断通往该城市的所有道路后的DP值。
 * 找出DP值最小的城市
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x].add(y);
            graph[y].add(x);
        }
        int minDP = N;
        List<Integer> minCities = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int maxGroup = 0;
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            for (int j : graph[i]) {
                int group = dfs(graph, visited, j);
                maxGroup = Math.max(maxGroup, group);
            }
            if (maxGroup < minDP) {
                minDP = maxGroup;
                minCities.clear();
                minCities.add(i);
            } else if (maxGroup == minDP) {
                minCities.add(i);
            }
        }
        for (int city : minCities) {
            System.out.println(city);
        }
    }

    private static int dfs(List<Integer>[] graph, boolean[] visited, int node) {
        visited[node] = true;
        int count = 1;
        for (int next : graph[node]) {
            if (!visited[next]) {
                count += dfs(graph, visited, next);
            }
        }
        return count;
    }
}
