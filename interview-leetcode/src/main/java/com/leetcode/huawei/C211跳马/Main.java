package com.leetcode.huawei.C211跳马;
/*题目描述输入 m 和 n 两个数，m 和 n 表示一个 m*n 的棋盘。输入棋盘内的数据。棋盘中存在数字和“.“两种字符，如果是数字表示该位置是
        一匹马，如果是“.“表示该位置为空的，棋盘内的数字表示为该马能走的最大步数。
        例如棋盘内某个位置一个数字为 k, 表示该马只能移动 1~k 步的距离。
        棋盘内的马移动类似于中国象棋中的马移动，先在水平或者垂直方向上移动一格，然后再将其移动到对角线位置。
        棋盘内的马可以移动到同一个位置，同一个位置可以有多匹马。
        请问能否将棋盘上所有的马移动到同一个位置，若可以请输入移动的最小步数。若不可以输出 0。
        输入描述
        输入m 和 n 两个数，m 和 n 表示一个 m*n 的棋盘。输入棋盘内的数据。
        输出描述
        能否将棋盘上所有的马移动到同一个位置，若可以请输入移动的最小步数。若不可以输出 0 。*/

/**
 * 读取棋盘的大小和棋盘的数据。
 * 找出所有马的位置，将这些位置作为BFS的起始节点。
 * 对每个起始节点，使用BFS搜索所有可以到达的位置，记录到达每个位置的最小步数。
 * 对每个位置，如果所有马都可以到达该位置，那么该位置就是一个可能的目标位置，记录所有马到达该位置的步数之和。
 * 找出所有可能的目标位置中，步数之和最小的位置，就是最终的目标位置。
 * 如果没有找到目标位置，就输出0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;

public class Main {
    // 定义棋盘的行数和列数
    private static int m, n;
    // 定义棋盘
    private static int[][] board;
    // 定义马的位置和步数的列表
    private static LinkedList<int[]> horses = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        // 使用BufferedReader读取输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 读取第一行输入，获取棋盘的行数和列数
        String[] firstLine = br.readLine().split(" ");
        m = Integer.parseInt(firstLine[0]);
        n = Integer.parseInt(firstLine[1]);
        // 初始化棋盘
        board = new int[m][n];

        // 读取棋盘上每个位置的输入
        for (int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                // 如果当前位置不是空点，则将马的位置和步数添加到列表中
                if (!line[j].equals(".")) {
                    horses.add(new int[]{i, j, Integer.parseInt(line[j])});
                }
            }
        }

        // 调用bfs方法并打印结果
        System.out.println(bfs());
    }

    // 定义广度优先搜索方法
    private static int bfs() {
        // 定义马能走的八个方向
        int[][] directions = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
        // 初始化最小步数为最大值
        int minSteps = Integer.MAX_VALUE;

        // 遍历棋盘上的每个位置
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 初始化当前位置的步数为0
                int steps = 0;
                // 标记是否所有马都能到达当前位置
                boolean possible = true;

                // 遍历每个马
                for (int[] horse : horses) {
                    // 使用队列进行BFS
                    Queue<int[]> queue = new LinkedList<>();
                    // 使用集合记录已访问的位置
                    Set<String> visited = new HashSet<>();
                    // 将当前马的位置和步数0加入队列
                    queue.offer(new int[]{horse[0], horse[1], 0});
                    // 将当前马的位置添加到已访问集合中
                    visited.add(horse[0] + "," + horse[1]);
                    // 标记是否找到当前位置
                    boolean found = false;

                    // 当队列不为空且可能到达当前位置时
                    while (!queue.isEmpty() && possible) {
                        // 取出队列头部元素
                        int[] current = queue.poll();
                        // 如果当前元素位置等于目标位置
                        if (current[0] == i && current[1] == j) {
                            // 累加步数
                            steps += current[2];
                            // 标记为找到
                            found = true;
                            break;
                        }

                        // 遍历马能走的八个方向
                        for (int[] dir : directions) {
                            // 计算新的位置
                            int nx = current[0] + dir[0];
                            int ny = current[1] + dir[1];
                            // 如果新位置有效且未访问过，则加入队列
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && current[2] < horse[2] && !visited.contains(nx + "," + ny)) {
                                queue.offer(new int[]{nx, ny, current[2] + 1});
                                visited.add(nx + "," + ny);
                            }
                        }
                    }

                    // 如果没有找到目标位置，则标记为不可能到达
                    if (!found) {
                        possible = false;
                    }
                }

                // 如果所有马都能到达当前位置，则更新最小步数
                if (possible) {
                    minSteps = Math.min(minSteps, steps);
                }
            }
        }

        // 如果最小步数为最大值，则返回-1，否则返回最小步数
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }
}
