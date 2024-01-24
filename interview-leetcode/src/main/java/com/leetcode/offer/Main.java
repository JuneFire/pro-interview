package com.leetcode.offer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();

        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            }
        }

        List<String> result = new ArrayList<>();
        for (char c : countMap.keySet()) {
            result.add(c + String.valueOf(countMap.get(c)));
        }

        result.sort((s1, s2) -> {
            int count1 = Integer.parseInt(s1.substring(1));
            int count2 = Integer.parseInt(s2.substring(1));
            if (count1 != count2) {
                return count2 - count1;
            } else {
                return s1.charAt(0) - s2.charAt(0);
            }
        });

        for (String s : result) {
            System.out.print(s);
        }
    }
}
