package leetcode.BinaryTree;

/**
 * @Author: zkcheng
 * @Date: 2022/08/05/22:27
 * @Description:
 */
public class CreateTree {

    static int i = 0;// 计数

    public static TreeNode createBinaryTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        TreeNode node = new TreeNode();
        if (i < arr.length) {// 如果二叉树数据空值正确，此判断可以省略
            node.data = arr[i++];
            if (node.data == 0) {
                return null;
            } else {
                node.leftChild = createBinaryTree(arr);
                node.rightChild = createBinaryTree(arr);
            }
        }
        return node;
    }

}
