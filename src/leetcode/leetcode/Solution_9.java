package src.leetcode.leetcode;

import src.leetcode.node.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_9 {

    public boolean isUnivalTree(TreeNode root) {

        if (root == null) return false;
        int origin = root.val;
        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        TreeNode node;
        while (!stack.isEmpty()){
            node = stack.poll();
            if (node.val != origin) return false;
            if (node.left != null) stack.add(node.left);
            if (node.right != null) stack.add(node.right);
        }
        return true;
    }

    public static void main(String[] args){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(1);
        root.right.right = new TreeNode(1);

        Solution_9 solution = new Solution_9();
        System.out.println("isUnivalTree = " + solution.isUnivalTree(root));

    }

}
