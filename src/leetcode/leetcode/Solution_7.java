package src.leetcode.leetcode;

import src.leetcode.node.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_7 {

    public TreeNode insertIntoBST(TreeNode root, int val) {

        if (root == null || root.val == val) return root;

        TreeNode node,newNode;
        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            node = stack.poll();
            if (node.val > val){
                if (node.left == null){
                    newNode = new TreeNode(val);
                    node.left = newNode;
                    break;
                } else stack.add(node.left);
            } else if (node.val == val){
                break;
            } else {
                if (node.right == null){
                    newNode = new TreeNode(val);
                    node.right = newNode;
                } else stack.add(node.right);
            }
        }

        return root;
    }

    public static void main(String[] args){
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(7);
        treeNode.left.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(1);

        Solution_7 solution = new Solution_7();
        TreeNode root = solution.insertIntoBST(treeNode,5);
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
