package com.leetcode.huawei.low;

// 全量和已占用字符集 、字符串统计



import java.util.HashMap;
        import java.util.Map;
        import java.util.Scanner;

public class qlyz_zifu {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
        String input = "a:3,b:5,c:2@a:1,b:2";
        // 分割全量字符集和已占用字符集
        String[] sets = input.split("@");
        String fullCharacterSet = sets[0];
        String occupiedCharacterSet = sets[1];

        // 处理全量字符集
        Map<Character, Integer> fullCharacterMap = parseCharacterSet(fullCharacterSet);

        // 处理已占用字符集
        Map<Character, Integer> occupiedCharacterMap = parseCharacterSet(occupiedCharacterSet);

        // 输出剩余可用字符集
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : fullCharacterMap.entrySet()) {
            char character = entry.getKey();
            int remainingCount = entry.getValue() - occupiedCharacterMap.getOrDefault(character, 0);
            if (remainingCount > 0) {
                result.append(character).append(":").append(remainingCount).append(",");
            }
        }

        for(Map.Entry<Character, Integer> entry : fullCharacterMap.entrySet()){

        }

        System.out.print(result.substring(0,result.length() - 1));
    }

    private static Map<Character, Integer> parseCharacterSet(String characterSet) {
        Map<Character, Integer> characterMap = new HashMap<>();
        String[] characters = characterSet.split(",");
        for (String characterCount : characters) {
            String[] parts = characterCount.split(":");
            char character = parts[0].charAt(0);
            int count = Integer.parseInt(parts[1]);
            characterMap.put(character, count);
        }
        return characterMap;
    }
}
