package main.java.leetcode.Structure;

import java.util.Stack;

/**
 * @Author: zkcheng
 * @Date: 2021/07/04/16:43
 * @Description:
 */
public class StackAndQueue {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int k){
        stack1.push(k);
    }

    public int pop(){
        if(stack2.size() <= 0){
            while (stack2.size() != 0){
                stack2.push(stack1.pop());
            }
        }
       return stack2.pop();
    }
}
