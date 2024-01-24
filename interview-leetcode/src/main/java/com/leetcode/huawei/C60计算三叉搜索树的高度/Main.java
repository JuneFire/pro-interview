package com.leetcode.huawei.C60计算三叉搜索树的高度;
/*题目描述定义构造三叉搜索树规则如下：
        每个节点都存有一个数，当插入一个新的数时，从根节点向下寻找，直到找到一个合适的空节点插入。查找的规则是：

        · 如果数小于节点的数减去500，则将数插入节点的左子树· 如果数大于节点的数加上500，则将数插入节点的右子树·否则，将数插入节点的中子树

        给你一系列数，请按以上规则，按顺序将数插入树中，构建出一棵三叉搜索树」最后输出树的高度。输入描述
        第一行为一个数 N, 表示有 N 个数，$1\leq\mathbb{N}\leq10000$
        第二行为 N 个空格分隔的整数，每个数的范围为[1,10000]
        输出描述
        输出树的高度 (根节点的高度为1)
        用例1
        输入
         52 5000 2000 5000 8000 1800
        输出
         3*/

import java.util.Scanner;

class Node {
    int val;
    Node left, mid, right;
    Node(int val) {
        this.val = val;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Node root = new Node(sc.nextInt());
        int height = 1;
        for (int i = 1; i < N; i++) {
            height = Math.max(height, insert(root, sc.nextInt(), 1));
        }
        System.out.println(height);
    }

    static int insert(Node node, int val, int depth) {
        if (val < node.val - 500) {
            if (node.left == null) {
                node.left = new Node(val);
                return depth + 1;
            } else {
                return insert(node.left, val, depth + 1);
            }
        } else if (val > node.val + 500) {
            if (node.right == null) {
                node.right = new Node(val);
                return depth + 1;
            } else {
                return insert(node.right, val, depth + 1);
            }
        } else {
            if (node.mid == null) {
                node.mid = new Node(val);
                return depth + 1;
            } else {
                return insert(node.mid, val, depth + 1);
            }
        }
    }
}
