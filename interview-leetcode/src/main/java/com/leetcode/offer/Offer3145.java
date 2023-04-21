package com.leetcode.offer;

import com.leetcode.Structure.TreeNode;

import java.util.*;

public class Offer3145 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed == null || pushed.length <= 0){
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int k = 0; // 标记poped 的下标
        for (int j : pushed) {
            stack.push(j);
            while (!stack.isEmpty() && stack.peek() == popped[k]) {
                stack.pop();
                k++;
            }

        }
        return stack.isEmpty();
    }

    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        int i = 0, j = 0;
        for (int e : pushed) {
            pushed[i] = e;
            while (i >= 0 && pushed[i] == popped[j]) {
                j++;
                i--;
            }
            i++;
        }
        System.out.println(Arrays.toString(pushed));
        return i == 0;
    }

    public int[] levelOrder(TreeNode root) {
        if(root == null){
            return new int[0];
        }
        // 新建一个队列
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> list = new LinkedList<>();
        queue.offer(root);
        //循环判断队列
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if(node.left!= null) queue.offer(node.left);
            if(node.left!= null) queue.offer(node.right);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
