package com.leetcode.huawei.C61石头剪刀布游戏;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 存储每种出拳形状对应的玩家ID列表
        Map<String, List<String>> shapeToPlayerIds = new HashMap<>();
        // 用于读取输入数据
        Scanner scanner = new Scanner(System.in);
        // 循环读取玩家ID和出拳形状
        while (scanner.hasNext()) {
            String playerId = scanner.next(); // 玩家ID
            String shape = scanner.next();    // 出拳形状
            // 如果该出拳形状还未记录，则初始化玩家ID列表
            shapeToPlayerIds.putIfAbsent(shape, new ArrayList<>());
            // 将玩家ID添加到对应出拳形状的列表中
            shapeToPlayerIds.get(shape).add(playerId);
        }

        // 如果每种出拳形状都只有一种，或者三种都有，则判定为平局
        if (shapeToPlayerIds.size() != 2) {
            System.out.println("NULL");
            return;
        }

        // 存储胜利玩家ID的列表
        List<String> winningPlayerIds = new ArrayList<>();
        // 根据出拳规则，确定胜利玩家ID列表
        if (shapeToPlayerIds.containsKey("A") && shapeToPlayerIds.containsKey("B")) {
            winningPlayerIds = shapeToPlayerIds.get("A"); // A胜B
        } else if (shapeToPlayerIds.containsKey("B") && shapeToPlayerIds.containsKey("C")) {
            winningPlayerIds = shapeToPlayerIds.get("B"); // B胜C
        } else if (shapeToPlayerIds.containsKey("A") && shapeToPlayerIds.containsKey("C")) {
            winningPlayerIds = shapeToPlayerIds.get("C"); // C胜A
        } else { // 如果没有满足以上任何条件，则没有胜者
            System.out.println("NULL");
            return;
        }

        // 对胜利玩家ID进行排序
        Collections.sort(winningPlayerIds);
        // 输出胜利玩家ID
        for (String playerId : winningPlayerIds) {
            System.out.println(playerId);
        }
    }
}
