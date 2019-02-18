package com.jary.interview.leetcode;

import com.jary.interview.leetcode.node.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_8 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {

        return construct(nums,0,nums.length);

    }

    public TreeNode construct(int[] nums,int l,int r){

        if (l == r)
            return null;
        int max_i = maxIndex(nums, l, r);
        TreeNode root = new TreeNode(nums[max_i]);
        root.left = construct(nums, l, max_i);
        root.right = construct(nums, max_i + 1, r);
        return root;

    }

    public int maxIndex(int[] nums,int l,int r){

        int max_i = l;
        for (int i = l; i < r; i++) {
            if (nums[max_i] < nums[i])
                max_i = i;
        }
        return max_i;

    }

    public static void main(String[] args){
        int[] nums = {3,2,1,6,0,5};

        Solution_8 solution = new Solution_8();
        TreeNode root = solution.constructMaximumBinaryTree(nums);
        if (root == null) System.out.println("TreeNode is null!");
        TreeNode node;
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
