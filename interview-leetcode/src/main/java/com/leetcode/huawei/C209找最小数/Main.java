package com.leetcode.huawei.C209找最小数;
/*题目描述给一个正整数NUM1，计算出新正整数NUM2，NUM2为NUM1中移除N位数字后的结果，需要使得NUM2的值最小
        输入描述
        1.输入的第一行为一个字符串，字符串由0-9字符组成，记录正整数NUM1，NUM1长度小于32。
        2.输入的第二行为需要移除的数字的个数，小于NUM1长度。
        输出描述
        输出一个数字字符串，记录最小值NUM2。
        用例
        输入
        2615371
        4
        输出
        131*/

/**
 * 读取正整数NUM1和需要移除的数字的个数N。
 * 使用一个栈来保存结果，遍历NUM1的每一位数字，如果当前数字小于栈顶的数字并且还可以移除数字，就移除栈顶的数字，然后将当前数字入栈，直到不能再移除数字或者已经遍历完所有数字。
 * 如果还可以移除数字，就从栈顶开始移除，直到不能再移除数字。
 * 将栈中的数字从底到顶连接成一个字符串，就是结果NUM2
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = sc.nextLine();
        int N = sc.nextInt();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < num1.length(); i++) {
            while (!stack.isEmpty() && N > 0 && stack.peekLast() > num1.charAt(i)) {
                stack.removeLast();
                N--;
            }
            stack.addLast(num1.charAt(i));
        }
        while (N-- > 0) {
            stack.removeLast();
        }
        StringBuilder num2 = new StringBuilder();
        while (!stack.isEmpty()) {
            num2.append(stack.removeFirst());
        }
        System.out.println(num2.toString());
    }
}
