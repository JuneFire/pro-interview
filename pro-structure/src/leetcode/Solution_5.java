package leetcode;

import com.jary.interview.leetcode.node.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_5 {

    public int rangeSumBST(TreeNode root, int L, int R) {

        if (root == null) return 0;
        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        int size = 0,sum = 0;
        TreeNode node;
        while (!stack.isEmpty()){
            node = stack.poll();
            size++;
            if (node.val >= L && node.val <= R) sum += node.val;
            if (size >= 10000) throw new IllegalArgumentException("Tree size is more than 10000");
            if(sum >= (1 << 31 - 1)) throw new IllegalArgumentException("Tree sum is more than 2 ^ 31");
            if (node.left != null) stack.add(node.left);
            if (node.right != null) stack.add(node.right);
        }

        return sum;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.left.left.right = new TreeNode(18);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(6);
        Solution_5 solution = new Solution_5();
        System.out.println("sum = " + solution.rangeSumBST(root,6,10));

    }

}
