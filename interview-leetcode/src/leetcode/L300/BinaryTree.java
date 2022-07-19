package leetcode.L300;

import leetcode.Structure.TreeNode;

import java.util.*;

/**
 * @Author: zkcheng
 * @Date: 2022/07/12/10:59
 * @Description:
 */
public class BinaryTree {

    // 中序遍历
    public List<Integer> inorderTraversalWithLoop(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);   // 这个决定了遍历顺序
            root = root.right;
        }
        return res;
    }

    // 后续遍历
    public List<Integer> postorderTraversalWithLoop(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prevAccess = null; // 前一个访问节点
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prevAccess) {
                res.add(root.val);
                prevAccess = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    // 镜像二叉树
    boolean deepCheck(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return deepCheck(left.left, right.right) && deepCheck(left.right, right.left);
    }

    // 平衡二叉树
    public boolean ret = true;
    public boolean isBalanced(TreeNode root){
        height(root);
        return ret;
    }
    private int height(TreeNode root){
        if(!ret) return 0;
        if(root == null) return 0;
        int right = height(root.right);
        int left = height(root.left);
        if(Math.abs(right - left) > 1) {
            ret = false;
        }
        return Math.max(right, left) + 1;
    }

    // 反转二叉树
    public TreeNode invertTree(TreeNode root){
        if(root == null) return null;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}
