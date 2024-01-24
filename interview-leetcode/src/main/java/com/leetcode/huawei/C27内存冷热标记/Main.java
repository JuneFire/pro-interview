package com.leetcode.huawei.C27内存冷热标记;
/*

题目描述现代计算机系统中通常存在多级的存储设备，针对海量 workload 的优化的一种思路是将热点内存页优先放到快速存储层级，这就需要对
        内存页进行冷热标记。
        一种典型的方案是基于内存页的访问频次进行标记，如果统计窗口内访问次数大于等于设定阈值，则认为是热内存页，否则是冷内存页，
        对于统计窗口内跟踪到的访存序列和阈值，现在需要实现基于频次的冷热标记。内存页使用页框号作为标识。
        输入描述
        第一行输入为 N,表示访存序列的记录条数，$0<\mathbb{N}\leq10000$
        第二行为访存序列，空格分隔的 N 个内存页框号
        第三行为阈値
        输出描述第一行输出标记为热内存的内存页个数，如果没有被标记的热内存页，则输出 O 。

        如果第一行>0,则接下来按照访问频次降序输出内存页框号，一行一个，频次一样的页框号，页框号小的排前面。
*/

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 第一行输入N
        int N = sc.nextInt();
        // 第二行输入访客序列
        int[] visitors = new int[N];
        for (int i = 0; i < N; i++) {
            visitors[i] = sc.nextInt();
        }
        // 第三行输入阈值
        int threshold = sc.nextInt();

        // 对访客序列进行排序
        Arrays.stream(visitors).sorted();
//        Map<Integer, Integer> map = new HashMap<>();
//        Set<Integer> set = new HashSet<>();
        Set<Integer> list = new HashSet<>();
        int tmp = 0;
        for (int i = 0; i < visitors.length; i++) {
            if ((i > 0 && visitors[i] == visitors[i - 1]) || (i < visitors.length - 1 && visitors[i] == visitors[i + 1])) {
                tmp++;
                if (tmp >= threshold) {
                    list.add(visitors[i]);
                }
            } else {
                tmp = 0;
            }

        }
        System.out.println(list.size());
        System.out.println(list.toString());

    }

}

/*

import java.util.*;

public class Main.java {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        String[] accesses = scanner.nextLine().split(" ");
        int threshold = Integer.parseInt(scanner.nextLine());
        scanner.close();

        // 使用 TreeMap 来存储内存页框号和对应的访问次数
        // TreeMap 默认按照 key 升序排列，这里我们需要按照访问次数降序，页框号升序排列
        Map<Integer, Integer> frequencyMap = new TreeMap<>();
        for (String access : accesses) {
            int pageFrame = Integer.parseInt(access);
            frequencyMap.put(pageFrame, frequencyMap.getOrDefault(pageFrame, 0) + 1);
        }

        // 使用 PriorityQueue 来对内存页框号进行排序
        PriorityQueue<Integer> hotPages = new PriorityQueue<>((a, b) -> {
            int freqCompare = frequencyMap.get(b).compareTo(frequencyMap.get(a));
            if (freqCompare == 0) {
                return a.compareTo(b);
            }
            return freqCompare;
        });

        // 将达到阈值的热内存页加入到优先队列中
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() >= threshold) {
                hotPages.offer(entry.getKey());
            }
        }

        // 输出结果
        int hotCount = hotPages.size();
        System.out.println(hotCount);
        while (!hotPages.isEmpty()) {
            System.out.println(hotPages.poll());
        }
    }
}
*/
