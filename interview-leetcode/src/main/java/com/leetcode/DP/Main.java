package com.leetcode.DP;

/*实现一个模拟目录管理功能的软件，输入一个命令序列，输出最后一条命令运行结果。
        支持命令：
        1）创建目录命令：mkdir 目录名称，如mkdir abc为在当前目录创建abc目录，如果已存在同名目录则不执行任何操作。此命令无输出。
        2）进入目录命令：cd 目录名称, 如cd abc为进入abc目录，特别地，cd ..为返回上级目录，如果目录不存在则不执行任何操作。此命令无输出。
        3）查看当前所在路径命令：pwd，输出当前路径字符串。
        约束：
        1）目录名称仅支持小写字母；mkdir和cd命令的参数仅支持单个目录，如：mkdir abc和cd abc；
           不支持嵌套路径和绝对路径，如mkdir abc/efg, cd abc/efg, mkdir /abc/efg, cd /abc/efg是不支持的。
        2）目录符号为/，根目录/作为初始目录。
        3）任何不符合上述定义的无效命令不做任何处理并且无输出。
    输入描述
        输入N行字符串，每一行字符串是一条命令。
    输出描述
        输出最后一条命令运行结果字符串。*/

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static String currentDirectory = "/";  // 当前所在路径
    private static HashSet<String > directoryList = new HashSet<>(); //当前存在的目录列表
    public static void main(String[] args) {
        // 创建一个扫描器来读取用户输入
        Scanner scanner = new Scanner(System.in);
        String lastCommand = ""; // 用于记录最后一条命令
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine(); // 读取一条命令
            lastCommand = executeCommand(command);
        }
        System.out.println(lastCommand);
    }

    public static String executeCommand(String command) {
        String[] commands = command.split(" ");
        String commandType = commands[0];
        if (commandType.equals("mkdir")) {
            if(commands.length != 2) {
                return "";
            }
            // 创建目录命令
            return createDirectory(commands[1]);
        }else if(commandType.equals("cd")) {
            if(commands.length != 2) {
                return "";
            }
            // 进入目录命令
            return changeDirectory(commands[1]);
        }else if(commandType.equals("pwd")) {
            // 查看当前所在路径命令
            return getCurrentDirectory();
        }else {
            // 无效命令
            return "";
        }
    }

    public static String createDirectory(String directoryName) {
        if (!directoryName.matches("[a-z]+")) {
            return "";
        } else if(directoryExists(directoryName)) {
            return "";
        }
        String curDir = currentDirectory +  directoryName + "/";
        directoryList.add(curDir); // 将新创建的目录加入目录列表
        return "";
    }

    public static String changeDirectory(String directoryName) {
        if (directoryName.equals("..")) {
            if (!currentDirectory.equals("/")) {
                int lastSlashIndex = currentDirectory.lastIndexOf("/");
                // 如果当前目录的上级目录是根目录，那么最终的目录就是根目录
                if(lastSlashIndex == 0) {
                    currentDirectory = "/";
                    return "";
                }
                // 截取当前目录的上级目录
                currentDirectory = currentDirectory.substring(0, lastSlashIndex);
                int lastlastSlashIndex = currentDirectory.lastIndexOf("/");
                if(lastlastSlashIndex == 0) {
                    currentDirectory = "/";
                    return "";
                }else {
                    // 否则，最终的目录就是当前目录的上级目录
                    currentDirectory = currentDirectory.substring(0, lastlastSlashIndex);
                }
            }
            return "";
        } else if (directoryName.matches("[a-z]+")) {
            String tmpDir = currentDirectory + directoryName + "/";
            if(directoryExists(tmpDir)) {
                return "";
            }
            currentDirectory =  tmpDir;
            return "";
        } else {
            return "";
        }
    }

    public static String getCurrentDirectory() {
        return currentDirectory;
    }

    // 判断一个目录是否存在
    public static boolean directoryExists(String directoryName) {
        String tempDir = currentDirectory + directoryName + "/";
        if(directoryList.contains(tempDir)) {
            return true;
        }else {
            return false;
        }

    }
}
