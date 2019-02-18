package com.jary.interview.leetcode;

import com.jary.interview.leetcode.node.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_12 {

    public TreeNode pruneTree(TreeNode root) {

        return containsOne(root) ? root : null;
    }

    public boolean containsOne(TreeNode treeNode){

        if (treeNode == null) return false;

        boolean left = containsOne(treeNode.left);
        boolean right = containsOne(treeNode.right);
        if (!left) treeNode.left = null;
        if (!right) treeNode.right = null;
        return treeNode.val == 1 || left || right;

    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left =  new TreeNode(0);
        root.right.right = new TreeNode(1);
        Solution_12 solution = new Solution_12();
        TreeNode node = solution.pruneTree(root);
        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            node = stack.poll();
            System.out.println(node.val);
            if (node.left != null) stack.add(node.left);
            if (node.right != null) stack.add(node.right);
        }
    }

}
