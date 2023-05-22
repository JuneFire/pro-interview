package com.leetcode.offer;

import com.leetcode.Structure.TreeNode;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Offer3145 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed == null || pushed.length <= 0){
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int k = 0; // 标记poped 的下标
        for (int j : pushed) {
            stack.push(j);
            while (!stack.isEmpty() && stack.peek() == popped[k]) {
                stack.pop();
                k++;
            }

        }
        return stack.isEmpty();
    }

    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        int i = 0, j = 0;
        for (int e : pushed) {
            pushed[i] = e;
            while (i >= 0 && pushed[i] == popped[j]) {
                j++;
                i--;
            }
            i++;
        }
        System.out.println(Arrays.toString(pushed));
        return i == 0;
    }

    public int[] levelOrder(TreeNode root) {
        if(root == null){
            return new int[0];
        }
        // 新建一个队列
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> list = new LinkedList<>();
        queue.offer(root);
        //循环判断队列
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if(node.left!= null) queue.offer(node.left);
            if(node.left!= null) queue.offer(node.right);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     */
    public boolean verifyPostorder(int[] postorder) {
        if(postorder == null){
            return true;
        }

        return f(postorder, 0, postorder.length - 1);
    }

    boolean f(int[] postorder, int i, int j){
        if(i >= j){
            return true;
        }

        int root = postorder[j];
        int p = i;
        // 获取第一个大于或者等于 root 等元素的位置
        while(postorder[p] < root) p++;
        // 判断 p ~ j -1 这个范围是否存在小于root的元素
        for(int k = p; k < j; k++){
            if(postorder[k] < root){
                return false;
            }
        }

        return f(postorder, i, p - 1) && f(postorder, p, j - 1);
    }


    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     * @param root
     * @param target
     * @return
     */
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root, target);
        return res;
    }
    // 深度优先搜索
    public void dfs(TreeNode root, int target){
        if(root == null){
            return;
        }

        path.offerLast(root.val);  // 从队列后面增加
        target -= root.val;  // 当前的目标值减去搜索的值
        if(root.left == null && root.right == null && target == 0){
            res.add(new LinkedList<>(path));  // 此路是通的
        }

        dfs(root.left, target);
        dfs(root.right, target);
        path.pollLast();  // 回溯，删除最后一个
    }

    // 广度优先搜索

    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>(); // 用map 存节点路径

    public List<List<Integer>> pathSum2(TreeNode root, int target) {
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queueNode = new LinkedList<TreeNode>();
        Queue<Integer> queueSum = new LinkedList<Integer>();
        queueNode.offer(root);
        queueSum.offer(0);

        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int rec = queueSum.poll() + node.val;

            if (node.left == null && node.right == null) {
                if (rec == target) {
                    getPath(node);
                }
            } else {
                if (node.left != null) {
                    map.put(node.left, node);
                    queueNode.offer(node.left);
                    queueSum.offer(rec);
                }
                if (node.right != null) {
                    map.put(node.right, node);
                    queueNode.offer(node.right);
                    queueSum.offer(rec);
                }
            }
        }

        return ret;
    }

    public void getPath(TreeNode node) {
        List<Integer> temp = new LinkedList<Integer>();
        while (node != null) {
            temp.add(node.val);
            node = map.get(node);
        }
        Collections.reverse(temp);
        ret.add(new LinkedList<Integer>(temp));
    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     * A-A1-B-B1-C-C1-D-D1
     */
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        // 复制链表节点
        Node cur = head;
        while(cur != null){
            Node next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }

        // 复制随机节点
        cur = head;
        while(cur != null){
            Node curNew = cur.next;
            curNew.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }

        // 拆分,比如把 A->A1->B->B1->C->C1拆分成 A->B->C和A1->B1->C1
        Node headNew = head.next;
        cur = head;
        Node curNew = head.next;
        while(cur != null){
            // A->B->C
            cur.next = cur.next.next;
            cur = cur.next;
            // A1->B1->C1
            curNew.next = cur == null ? null : cur.next;
            curNew = curNew.next;
        }

        return headNew;
    }


    //剑指 Offer 36. 二叉搜索树与双向链表
    public LinkNode treeToDoublyList(LinkNode root){
        // 将该二叉搜索树转换成一个排序的循环双向链表。

        Queue<LinkNode> queue = new LinkedList<>();
        //中序遍历填充队列
        inorder(root, queue);
        // 给队列中元素链接起来
        LinkNode head = queue.poll();
        LinkNode pre = head;
        while (!queue.isEmpty()){
            LinkNode cur = queue.poll();
            cur.left = pre;
            pre.right = cur;
            pre = cur;
        }

        pre.right = head;
        head.left = pre;

        return head;
    }

     void inorder(LinkNode root, Queue<LinkNode> queue){
        if(root == null){
            return;
        }
        inorder(root.left, queue);
        queue.add(root);
        inorder(root.right, queue);
    }

    //剑指 Offer 37. 序列化二叉树
    /**
     *
     * @param root
     * @return [1,2,3,null,null,4,5]
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node != null){
                builder.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }else {
                builder.append("null,");
            }
        }
        // TODO 删除末尾所有的 null
        String str = builder.toString();
        String[] split = str.split(",");
        int i = split.length - 1;
        while (split[i].equals("null")){
            i--;
        }
        List<String> collect = Arrays.stream(split).limit(i + 1).collect(Collectors.toList());

        String join = String.join(",", collect);
        return join;
    }

//    public String serialize2(TreeNode root) {
//        if(root == null) return "[]";
//        StringBuilder res = new StringBuilder("[");
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while(!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            if(node != null) {
//                res.append(node.val + ",");
//                queue.add(node.left);
//                queue.add(node.right);
//            }
//            else res.append("null,");
//        }
//        res.deleteCharAt(res.length() - 1);
//        res.append("]");
//        return res.toString();
//    }



    /**
     * 将[1,2,3,null,null,4,5] 转为 树
     * @param data
     * @return
     */
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() <= 0){
            return null;
        }
        String[] s = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i <= s.length - 1){
            TreeNode node = queue.poll();
            if(!s[i].equals("null")){
                TreeNode left = new TreeNode(Integer.parseInt(s[i]));
                node.left = left;
                queue.add(left);
            }
            i++;

            if(!s[i].equals("null")){
                TreeNode right = new TreeNode(Integer.parseInt(s[i]));
                node.right = right;
                queue.add(right);
            }
            i++;
        }

        return root;
    }

}
