package com.leetcode.DP;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

 class Node{
    String name;
    Map<String,Node> children = new HashMap<>();
    Node(String name, Node parent) {
        this.name = name;
        if (parent != null) {
            this.children.put("..", parent);
        }
    }
}
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node root = new Node("/", null);
        Node cur = root;
        String lastCommand = "";

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (command.isEmpty()) {
                break;
            }
            String[] parts = command.split(" ");
            String commandType = parts[0];

            if (commandType.equals("mkdir")) {
                if (parts.length != 2 || !isVaildName(parts[1])) {
                    continue;
                }
                cur.children.put(parts[1], new Node(cur.name + parts[1] + "/", cur));
                // create
//                createDirectory(cur,parts[1]);
            } else if (commandType.equals("cd")) {
                if (parts.length != 2 || !isVaildChange(parts[1])) {
                    continue;
                }
                // change
                Node nodeNext = cur.children.get(parts[1]);
                if (nodeNext != null) {
                    cur = nodeNext;
                }
            } else if (commandType.equals("pwd") && parts.length == 1) {
                // pwd
                lastCommand = cur.name;
            }
        }
        System.out.println(lastCommand);
    }


    private static boolean isVaildName(String directoryName) {
        return directoryName.matches("[a-z]+");
    }
    private static boolean isVaildChange(String name){
        return name.equals("..") || isVaildName(name);
    }
}
