package com.leetcode.huawei.C49API集群负载统计;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(sc.next());
        }
        int L = sc.nextInt();
        String key = sc.next();
        int count = 0;
        for (int i = 0; i < N; i++) {
            String url = list.get(i);
            String[] split = url.split("/");
            for (int j = 1; j < split.length; j++) {
                if (split[j].equals(key)) {
                    count++;
                }
            }

        }
        System.out.println(count);

    }
}
