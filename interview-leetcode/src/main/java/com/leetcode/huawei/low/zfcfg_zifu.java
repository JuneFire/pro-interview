package com.leetcode.huawei.low;


// 字符串分割转换

public class zfcfg_zifu {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // 读取输入
//        int K = Integer.parseInt(scanner.nextLine());
//        String S = scanner.nextLine();
        int K = 3;
        String S = "12abc-abCABc-4aB@";


        // 分割字符串
        String[] parts = S.split("-");

        // 处理第一个子串
        String firstPart = parts[0];
        StringBuilder otherPart = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            otherPart.append(parts[i]);
        }

        // 处理剩余的子串
        StringBuilder newPart = new StringBuilder();

        // 每K个字符分割
        for (int j = 0; j < otherPart.length(); j += K) {
            int endIndex = Math.min(j + K, otherPart.length());
            String subStr = otherPart.substring(j, endIndex);

            // 统计小写字母和大写字母数量
            long lowercaseCount = subStr.chars().filter(Character::isLowerCase).count();
            long uppercaseCount = subStr.chars().filter(Character::isUpperCase).count();

            // 根据条件转换字母
            if (lowercaseCount > uppercaseCount) {
                newPart.append(subStr.toLowerCase()).append("-");
            } else if (lowercaseCount < uppercaseCount) {
                newPart.append(subStr.toUpperCase()).append("-");
            } else {
                newPart.append(subStr).append("-");
            }
        }
        // 输出结果
        StringBuilder result = new StringBuilder();
        result.append(parts[0]).append("-").append(newPart);


        System.out.println(result.substring(0, result.length() - 1));

    }
}


