package main.java.leetcode.Structure;

/**
 * @Author: zkcheng
 * @Date: 2021/06/23/21:05
 * @Description:
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public void addNode(TreeNode n) {
        if (n.val < this.val) {
            if (this.left == null)
                this.left = n;
            else
                this.left.addNode(n);//递归，实现左，右，根节点的链接
        } else {
            if (right == null) {
                right = n;
            } else {
                right.addNode(n);
            }
        }
    }

    public void inOrder()//中序遍历
    {
        if (left != null) left.inOrder();
        System.out.print(val);
        if (right != null) right.inOrder();
    }
}

