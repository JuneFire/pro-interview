package com.leetcode.huawei.C47拼接URL;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // 读取输入的url前缀和url后缀
            String line = scanner.nextLine();
            String[] split = line.split(",");

            // 如果没有输入前缀和后缀，则输出"/"
            if (split.length == 0) {
                System.out.println("/");
                return;
            }

            // 获取前缀和后缀
            String prefix = split[0];
            String suffix = split[1];

            // 检查前缀结尾和后缀开头是否有"/"
            boolean prefixHasSlash = prefix.endsWith("/");
            boolean suffixHasSlash = suffix.startsWith("/");

            // 拼接url
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(prefix);

            // 如果前缀结尾和后缀开头都没有"/"，则补上"/"
            if (!prefixHasSlash && !suffixHasSlash) {
                urlBuilder.append("/");
            }

            urlBuilder.append(suffix);

            // 去重"/"
            String url = urlBuilder.toString().replaceAll("/+", "/");  // 正则表达式：/+
            System.out.println(url);
        }
    }
}

