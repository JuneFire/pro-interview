package com.leetcode.huawei.C30数组去重和排序;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] nums = line.split(",");
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        // hashMap put
        for (int i = 0; i < nums.length; i++) {
            int key = Integer.parseInt(nums[i]);
            hashMap.put(key, hashMap.getOrDefault(key, 0) + 1);
        }

        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(hashMap.entrySet()); // map转list

        list.sort((o1,o2)->{
            int a = o1.getValue();
            int b = o2.getValue();
            if(a == b){
                return o1.getKey() - o2.getKey();
            }
            return b - a;   // 按value降序排序
        });

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Integer> entry: list){
            sb.append(entry.getKey()).append(",");
        }
        System.out.println(sb.substring(0,sb.length()-1));
    }
}
