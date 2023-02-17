package leetcode.BinaryTree;

import leetcode.TreeNodeMethod.Node;

/**
 * @Author: zkcheng
 * @Date: 2022/08/05/22:27
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        // 二叉树的值保存在数组中，以0作为分隔，数字0表示空节点，数组
        int[] arr = new int[]{1, 2, 0, 3, 4, 0, 0, 0, 5, 6, 0, 0, 7, 8, 9, 0, 0, 0, 0};
        TreeNode binaryTree = CreateTree.createBinaryTree(arr);
//        System.out.println(binaryTree);
        PreOrderTraversal(binaryTree);
    }

    public static void PreOrderTraversal(TreeNode root) {
        if (root != null && root.data != 0) {
            PreOrderTraversal(root.leftChild);
            System.out.print(root.data + "\t");
            PreOrderTraversal(root.rightChild);
        }
    }

}
