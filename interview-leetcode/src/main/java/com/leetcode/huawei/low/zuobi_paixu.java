package com.leetcode.huawei.low;

import java.util.*;

// 相邻分数差值计算
public class zuobi_paixu {
    public static void main(String[] args) {
        Map<Integer, Integer> myMap = new HashMap<>();
        myMap.put(1, 10);
        myMap.put(2, 5);
        myMap.put(3, 6);
        myMap.put(4, 7);
        myMap.put(5, 12);

//        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(myMap.entrySet());
        List<Map.Entry<Integer, Integer>> mapList = new ArrayList<>(myMap.entrySet());
        mapList.sort(Comparator.comparingInt(Map.Entry::getValue));  // 按照值进行比较

        // 找出差值最小的两个值对应的键
        int minDifference = Integer.MAX_VALUE;
        List<Integer> keysWithMinDifference = new ArrayList<>();

        for (int i = 1; i < mapList.size(); i++) {
            int diff = Math.abs(mapList.get(i).getValue() - mapList.get(i - 1).getValue());

            if(diff < minDifference){
                minDifference = diff;
                keysWithMinDifference.clear();
                keysWithMinDifference.add(mapList.get(i - 1).getKey());
                keysWithMinDifference.add(mapList.get(i).getKey());
            } else if (diff == minDifference) {
                keysWithMinDifference.add(mapList.get(i - 1).getKey());
                keysWithMinDifference.add(mapList.get(i).getKey());
            }
        }

        System.out.println("Keys with minimum value difference: " + keysWithMinDifference);

    }
}
