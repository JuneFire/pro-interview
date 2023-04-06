package main.java.niukewang.stack;

import java.util.Stack;

/**
 * @author zkCheng
 * @date 2021/12/22 13:59
 * 栈
 */
public class StackCode {

    /**
     * 输入数组A 表示入栈顺序， 输入数组B，表示出栈方式， 判断，该出栈方式是否可行
     * [1,2,3,4,5]  [4,5,3,2,1]
     *
     * 解决方案： 使用一个辅助栈，顺序存储A入栈，同时遍历辅助栈的栈顶是否等于B的出栈顺序， true则一直出栈，直到辅助栈为空，否则flase
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA.length == 0 || popA.length == 0){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        // 用于标识弹出序列的位置
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.empty() && stack.peek() == popA[popIndex]){
                stack.pop(); // 出栈
                popIndex++;  // 弹向序列后一位
            }
        }
        return stack.empty();
    }

    public static void main(String[] args){

    }
}
