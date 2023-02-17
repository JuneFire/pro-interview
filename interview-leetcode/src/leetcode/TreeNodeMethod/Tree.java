package leetcode.TreeNodeMethod;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: zkcheng
 * @Date: 2022/08/05/22:11
 * @Description:
 */
public class Tree extends AbstractTree {


    private Node root;//root为根

    @Override
    //查询
    public Node find(int key) {
        Node current = root;//current是当前节点
        while (current != null) {
            if (current.data > key) {
                current = current.leftNode;
            } else if (current.data < key) {
                current = current.rightNode;
            } else {
                return current;//返回当前树(节点)
            }
        }
        return null;
    }



    @Override
    //插入 二叉排序树
    public boolean insert(int data) {
        count++;//记录一共多少节点
        //如果第一个节点为空 设置第一个节点
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node current = root;
        Node parentNode = null;

        while (current != null) {
            parentNode = current;
            //当前值比新插入值大
            if (current.data > data) {
                current = current.leftNode;
                //若左节点为空 则直接插入即可
                if (current == null) {
                    parentNode.leftNode = newNode;
                    return true;
                }
            } else {
                //当前值小于新插入值
                current = current.rightNode;
                if (current == null) {
                    parentNode.rightNode = newNode;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 删除共三种情况
     * 1 该节点是叶子节点
     * 2 该节点有一个叶子节点
     * 3 该节点有两个叶子节点
     *
     * @param data
     */
    @Override
    public boolean delete(int data) {
        Node current = root;
        Node parentNode = root;
        //当前节点是否为左节点
        boolean isLeftNode = false;

        //定位data的位置
        while (current.data != data) {
            parentNode = current;
            if (current.data > data) {
                isLeftNode = true;
                current = current.leftNode;
            } else {
                isLeftNode = false;
                current = current.rightNode;
            }

            if (current == null) {
                return false;
            }
        }
        // 1 第一种情况 此节点为叶子节点
        if (current.leftNode == null && current.rightNode == null) {
            if (current == root) {
                root = null;
            } else if (isLeftNode) {
                //如果要删除的节点为父节点的左节点 把父节点的左节点置为空
                parentNode.leftNode = null;
            } else {
                parentNode.rightNode = null;
            }
            return true;
        }
        //2 当前节点有一个节点
        if (current.leftNode == null && current.rightNode != null) {
            if (root == current) {
                root = current.rightNode;
            } else if (isLeftNode) {
                parentNode.leftNode = current.rightNode;
            } else {
                parentNode.rightNode = current.rightNode;
            }
        } else if (current.leftNode != null && current.rightNode == null) {
            if (root == current) {
                root = current.leftNode;
            } else if (isLeftNode) {
                parentNode.leftNode = current.leftNode;
            } else {
                parentNode.rightNode = current.leftNode;
            }
        }

        //3 当前节点有两个节点

        if (current.leftNode != null && current.rightNode != null) {
            //获取删除节点的后继结点
            Node successor = getSuccessor(current);
            if (root == current) {
                root = successor;
            } else if (isLeftNode) {
                parentNode.leftNode = successor;
            } else {
                parentNode.rightNode = successor;
            }
        }
        return false;
    }

    /**
     * 获取要删除节点的后继节点
     *
     * @param delNode
     * @return
     */
    public Node getSuccessor(Node delNode) {

        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightNode;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftNode;
        }
        if (successor != delNode.rightNode) {
            successorParent.leftNode = successor.rightNode;
            successor.rightNode = delNode.rightNode;
        }
        return successor;
    }


    /**
     * 查询
     * <p>
     * author:bw
     */

    @Override
    /**
     * 循环先序遍历
     */
    public void PreOrderTraversal(Node root) {
        if (root != null) {
            System.out.print(root.data + "\t");
            PreOrderTraversal(root.leftNode);
            PreOrderTraversal(root.rightNode);
        }
    }

    @Override
    /**
     * 递归中序遍历
     *
     * @param Node
     */
    public void InOrderTraversal(Node root) {
        if (root != null) {
            InOrderTraversal(root.leftNode);
            System.out.print(root.data + "\t");
            InOrderTraversal(root.rightNode);
        }
    }

    @Override
    /**
     * 递归后序遍历
     *
     * @param Node
     */
    public void PostOrderTraversal(Node root) {
        if (root != null) {
            PostOrderTraversal(root.leftNode);
            PostOrderTraversal(root.rightNode);
            System.out.print(root.data + "\t");
        }
    }


    //层序遍历
    @Override
    /**
     根结点入队
     循环：结点出队，该结点左右左右儿子入队
     */
    public void LevelOrderTraversal(Node root) {
        Queue<Node> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        while (root != null) {
            Node node = queue.remove();
            System.out.print(node.data + "\t");

            if (node.leftNode != null) {
                queue.add(node.leftNode);
            }
            if (node.rightNode != null) {
                queue.add(node.rightNode);
            }

            root = queue.peek();
        }

    }


    /**
     * 获取最大宽度--即叶子节点的个数
     */
    @Override
    public int getMaxWidth(Node root) {
        if (root == null)
            return 0;
        Queue<Node> queue = new ArrayDeque<Node>();
        int maxWitdth = 1; // 最大宽度
        queue.add(root); // 入队

        while (true) {
            int len = queue.size(); // 当前层的节点个数
            if (len == 0)
                break;
            while (len > 0) {// 如果当前层，还有节点
                Node t = queue.poll();
                len--;
                if (t.leftNode != null)
                    queue.add(t.leftNode); // 下一层节点入队
                if (t.rightNode != null)
                    queue.add(t.rightNode);// 下一层节点入队
            }
            maxWitdth = Math.max(maxWitdth, queue.size());
        }
        return maxWitdth;
    }


    /**
     * 二叉树的深度
     */
    @Override
    public int getMaxDepth(Node root) {
        if (root == null)
            return 0;
        else {
            int left = getMaxDepth(root.leftNode);
            int right = getMaxDepth(root.rightNode);
            return 1 + Math.max(left, right);
        }
    }


}
