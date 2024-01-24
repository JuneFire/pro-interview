package com.leetcode.huawei.C22开源项目热度榜单;
/*
题目描述某个开源社区希望将最近热度比较高的开源项目出一个榜单，推荐给社区里面的开发者。对于每个开源项目，开发者可以进行关注
        (watch)、收藏(star)、fork、提issue、提交合并请求(MR)等。
        数据库里面统计了每个开源项目关注、收藏、fork、issue、MR的数量，开源项目的热度根据这5个维度的加权求和进行排序。

        $1\mid \mathcal{H} = ( $watch$* \#$watch$) + ( $wstar$* \#$star$) + ( $wfork$* \#$fork$) + ( $wissue$* \#$issue$) + ( $wmr$* \#$mr$) $ 旧表示热度值
        Wwatch、Wstar、Wfork、Wissue、Wmr分别表示5个统计维度的权重。
        #watch、#star、#fork、#issue、#mr分别表示5个统计维度的统计值。
        榜单按照热度值降序排序，对于热度值相等的，按照项目名字转换为全小写字母后的字典序排序 $(a^{\prime},b^{\prime},c^{\prime},...,x^{\prime},y^{\prime},z^{\prime})$。
        输入描述
        第一行输入为N，表示开源项目的个数，0<N<100。
        第二行输入为权重值列表，一共 5 个整型值，分别对应关注、收藏、fork、issue、MR的权重，权重取值 $0<W\leq50$。
        第三行开始接下来的 N 行为开源项目的统计维度，每一行的格式为：

        1| name nr_watch nr_start nr_fork nr_issue nr_mr
        其中 name 为开源项目的名字，由英文字母组成，长度 ≤ 50,其余 5 个整型值分别为该开源项目关注、收藏、fork、issue、MR的数量， 数量取值 $0< $nr$\leq 1000$。
        输出描述
        按照热度降序，输出开源项目的名字，对于热度值相等的，按照项目名字转换为全小写后的字典序排序 $(a^{\prime}>b^{\prime}>c^{\prime}>\ldots>x^{\prime}>y^{\prime}>$
        “Z”)。
*/


import java.util.*;

public class Main {
    static class Project {
        String name;
        int heat;

        Project(String name, int heat) {
            this.name = name;
            this.heat = heat;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] weights = new int[5];
        for (int i = 0; i < 5; i++) {
            weights[i] = scanner.nextInt();
        }
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int heat = 0;
            for (int j = 0; j < 5; j++) {
                heat += weights[j] * scanner.nextInt();
            }
            projects.add(new Project(name, heat));
        }
        Collections.sort(projects, (a, b) -> {
            if (a.heat != b.heat) {
                return b.heat - a.heat;
            } else {
                return a.name.toLowerCase().compareTo(b.name.toLowerCase());
            }
        });
        for (Project project : projects) {
            System.out.println(project.name);
        }
    }
}
/*
5
5 6 6 1 2
camila 13 88 46 26 169
grace 64 38 87 23 103
lucas 91 79 98 154 79
leo 29 27 36 43 178
ava 29 27 36 43 178

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main.java {
    public static void main(String[] args) {
        // 创建Scanner对象用于获取用户输入
        Scanner sc = new Scanner(System.in);

        // 读取项目数量n
        int n = sc.nextInt();

        // 创建并填充权重数组weights
        int[] weights = new int[5];
        for (int i = 0; i < 5; i++) {
            weights[i] = sc.nextInt(); // 读取每个权重并存储到数组中
        }

        // 创建一个二维数组projects来存储项目的名称和热度值
        // 其中projects[i][0]存储项目名称，projects[i][1]存储项目热度
        String[][] projects = new String[n][2];

        // 读取项目信息并计算每个项目的热度
        for (int i = 0; i < n; i++) {
            projects[i][0] = sc.next(); // 存储项目名称

            int hot = 0; // 初始化项目热度为0
            // 读取项目的5个评分并计算热度
            for (int j = 0; j < 5; j++) {
                hot += sc.nextInt() * weights[j]; // 计算热度
            }
            projects[i][1] = String.valueOf(hot); // 将热度值转换为字符串并存储
        }

        // 使用自定义比较器对项目数组进行排序
        Arrays.sort(projects, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                // 解析字符串热度为整数
                int hotA = Integer.parseInt(a[1]);
                int hotB = Integer.parseInt(b[1]);
                // 首先根据热度值降序排序
                if (hotA != hotB) {
                    return hotB - hotA; // 热度高的项目排在前面
                } else {
                    // 如果热度相同，则根据项目名称字母顺序升序排序
                    return a[0].toLowerCase().compareTo(b[0].toLowerCase()); // 名称相同的项目按字母顺序排列
                }
            }
        });

        // 遍历排序后的项目数组并打印项目名称
        for (String[] project : projects) {
            System.out.println(project[0]); // 打印项目名称
        }

        // 关闭Scanner对象，释放资源
        sc.close();
    }
}
*/
