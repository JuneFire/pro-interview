package com.leetcode.offer.one;

import java.util.*;

/**
 * @author zkCheng
 * @date 2023/4/3 9:51
 * 剑指offer 3～15题
 */
public class Offer315 {
    /**
     * 剑指 Offer 03. 数组中重复的数字
     * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int repeat = -1;
        for (int num : nums) {
            if(!set.add(num)){
                repeat = num;
                break;
            }
        }
        return repeat;
    }
    // 其实是把num[i]作为锚位，对后面的数据一一进行比较，在比较的同时，把num[i] 放在 num[num[i]] 的位置上。
    public int findRepeatNumber2(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }



    /**
     * 剑指 Offer 04. 二维数组中的查找
     * https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
     */
    // 该矩阵类似二叉搜索树,可以以此进行行列比对，逐一消除行列
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int rows = matrix.length - 1;  // 行
        int cols = 0; // 列

        while (rows >= 0 && cols < matrix[0].length){
            if (matrix[rows][cols] < target){  // 消除当前列
                cols++;
            }else if(matrix[rows][cols] > target){  // 消除当前行
                rows--;
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 05. 替换空格
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder str = new StringBuilder();
        for (Character c : s.toCharArray()){
            if(c == ' ') {
                str.append("%20");
            }else {
                str.append(c);
            }
        }
        return str.toString();
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     */
    ArrayList<Integer> tmp = new ArrayList<Integer>();
    public int[] reversePrint(ListNode head) {
        recur(head);
        int[] res = new int[tmp.size()];
        for (int i = 0; i < res.length ; i++) {
             res[i] = tmp.get(i);
        }
        return res;
    }

    void recur(ListNode head){
        if(head.next == null) return;
        recur(head.next);
        tmp.add(head.val);
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public int[] reversePrint2(ListNode head) {
        LinkedList<Integer> stack = new LinkedList<>();
        while (head != null) {
            stack.addLast(head.val);
            head = head.next;
        }

        int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = stack.removeLast();

        return res;
    }


    /**
     * 剑指 Offer 07. 重建二叉树
     *
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     */
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    Map< Integer, Integer > map = new HashMap();
    public TreeNode buildTree(int[] preorder, int[] inorder){

        if(preorder == null || preorder.length <= 0){
            return null;
        }

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = treeNode(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return root;
    }

    TreeNode treeNode(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2){
        // 前序遍历或者中序遍历为空时，表示这棵树不存在，直接返回 null
        if(l1 > r1 || l2 > r2){
            return null;
        }
        // 根节点
        TreeNode root = new TreeNode(preorder[l1]);
        // 根节点在中序遍历中的下标
        int i =  map.get(preorder[l1]);
        // 递归求解
        root.left = treeNode(preorder, l1 + 1, l1 + i - l2, inorder, l2, i - 1);
        root.right = treeNode(preorder, l1 + (i - l2) + 1, r1, inorder, i + 1, r2);
        return root;
    }

    /**
     * 剑指 Offer 10- I. 斐波那契数列
     * 动态规划
     */
    public int fib(int n) {
        if (n <= 1) return n;
        int a = 0;
        int b = 0;
        int c = 1;
        // f(n) = f(n - 1) + f(n - 2)
        for(int i = 2; i <= n; i++){
            a = b;  // f(n - 2)
            b = c;  // f(n - 1)
            c = (a + b) % 1000000007;
        }
        return c;
    }

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     */
    public int minArray(int[] numbers) {
        // O（n)
        // logN O1
        int l = 0;
        int r = numbers.length - 1;
        while(l < r) {
            if(numbers[l] < numbers[r]){
                return numbers[l];
            }

            int mid = (r + l) / 2;
            if(numbers[mid] > numbers[l]){
                l = mid + 1;
            } else if(numbers[mid] < numbers[l]){
                r = mid;
            } else {
                l++;
            }
        }

        return numbers[l];
    }

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/
     */
    int n;
    int m;
    int len;
    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        this.n = board.length;
        this.m = board[0].length;
        this.len = word.length();
        visited = new boolean[n][m];
        // word从任意点开始

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(dfs(board, i, j, word, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    // 深度遍历
    boolean dfs(char[][] board, int i, int j,  String word, int k){
        // 判断该节点是否符合条件
        if(i < 0 || j < 0 || i >= n || j >= m || visited[i][j] || board[i][j] != word.charAt(k)){
            return false;
        }
        // word校验正常
        if( k == len - 1){
            return true;
        }
        // 记录遍历过的点
        visited[i][j] = true;
        // 深度遍历上下左右的点
        boolean res = dfs(board, i ,j + 1, word, k + 1) ||
                dfs(board, i + 1 ,j , word, k + 1) ||
                dfs(board, i ,j - 1, word, k + 1) ||
                dfs(board, i -  1 ,j , word, k + 1);
        // 失败后回溯复原遍历过的点
        visited[i][j] = false;
        // 返回结果
        return res;
    }

    /**
     * 剑指 Offer 14- I. 剪绳子
     * 动态规划
     */
    public int cuttingRope(int n) {
        if( n <= 1){
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 2; j < i; j++){
                // 其中，第一项j*(i-j)表示不剪的情况下，乘积为两段长度的乘积；第二项j*dp[i-j]表示剪了后，乘积为当前段长度j乘上剩余长度i-j的最大乘积。
                dp[i] = Math.max(dp[i], Math.max(j * (i - j) , j * dp[i - j] ));
            }
        }
        return dp[n];
    }

    /**
     * 剑指 Offer 14- II. 剪绳子 II
     * @param n
     * @return
     */
    public int maxProductAfterCutting(int n) {
        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return (int) (dp[n] % 1000000007);
    }

    /**
     * &运算符，只有对应的两个二进位均为1时，结果位才为1 ，否则为0。
     */
    public int hammingWeight(int n) {
        int sum = 0;
        while(n != 0){
            // n & (n - 1)可以消除n最右边的一个 1（二进制表示）
            n = n & (n - 1);
            sum ++;
        }
        return sum;
    }

}
