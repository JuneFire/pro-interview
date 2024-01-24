package com.leetcode.huawei.C65悄悄话;
/*题目描述给定一个二叉树，每个节点上站一个人，节点数字表示父节点到该节点传递悄悄话需要花费的时间。
        初始时，根节点所在位置的人有一个悄悄话想要传递给其他人，求二叉树所有节点上的人都接收到悄悄话花费的时间。
        输入描述
        给旋二叉树
        0 9 20 -1 -1 15 7 -1 -1 -1 -1 3 2 注：-1表示空节点
        输出描述
        二叉树所有节点上的人都接收到悄悄话花费的时间
        38*/


//这个问题可以通过深度优先搜索（DFS）来解决。我们可以从根节点开始，对每个节点，我们都计算出传递悄悄话到达该节点所需的时间，并更新总时间。
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nodes = sc.nextLine().split(" ");
        TreeNode root = buildTree(nodes, 0);
        int time = dfs(root, 0);
        System.out.println(time);
    }

    private static TreeNode buildTree(String[] nodes, int index) {
        if (index >= nodes.length || nodes[index].equals("-1")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(nodes[index]));
        node.left = buildTree(nodes, 2 * index + 1);
        node.right = buildTree(nodes, 2 * index + 2);
        return node;
    }

    private static int dfs(TreeNode node, int time) {
        if (node == null ) {
            return 0;
        }
        int leftTime = dfs(node.left, time + node.val);
        int rightTime = dfs(node.right, time + node.val);
        return node.val + Math.max(leftTime, rightTime);
    }
}
