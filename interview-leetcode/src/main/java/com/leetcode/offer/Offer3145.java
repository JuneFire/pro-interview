package com.leetcode.offer;

import com.leetcode.Structure.TreeNode;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Offer3145 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || pushed.length <= 0) {
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
        if (root == null) {
            return new int[0];
        }
        // 新建一个队列
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> list = new LinkedList<>();
        queue.offer(root);
        //循环判断队列
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.left != null) queue.offer(node.right);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

<<<<<<< HEAD

    //
    List<String> rec;
    boolean[] vis;

    public String[] permutation(String s) {
        int n = s.length();
        rec = new ArrayList<String>();
        vis = new boolean[n];
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuffer perm = new StringBuffer();
        backtrack(arr, 0, n, perm);
        int size = rec.size();
        String[] recArr = new String[size];
        for (int i = 0; i < size; i++) {
            recArr[i] = rec.get(i);
        }
        return recArr;
    }

    public void backtrack(char[] arr, int i, int n, StringBuffer perm) {
        if (i == n) {
            rec.add(perm.toString());
            return;
        }
        for (int j = 0; j < n; j++) {
            if (vis[j] || (j > 0 && !vis[j - 1] && arr[j - 1] == arr[j])) {
                continue;
            }
            vis[j] = true;
            perm.append(arr[j]);
            backtrack(arr, i + 1, n, perm);
            perm.deleteCharAt(perm.length() - 1);
            vis[j] = false;
        }
    }

    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        //单调队列
        //下面是要注意的点：
        //队列按从大到小放入
        //如果首位值（即最大值）不在窗口区间，删除首位
        //如果新增的值小于队列尾部值，加到队列尾部
        //如果新增值大于队列尾部值，删除队列中比新增值小的值，如果在把新增值加入到队列中
        //如果新增值大于队列中所有值，删除所有，然后把新增值放到队列首位，保证队列一直是从大到小
        if (nums.length == 0) return nums;

        Deque<Integer> deque = new LinkedList<>();
        int[] arr = new int[nums.length - k + 1];
        int index = 0;  //arr数组的下标
        //未形成窗口区间
        for (int i = 0; i < k; i++) {
            //队列不为空时，当前值与队列尾部值比较，如果大于，删除队列尾部值
            //一直循环删除到队列中的值都大于当前值，或者删到队列为空
            while (!deque.isEmpty() && nums[i] > deque.peekLast()) deque.removeLast();
            //执行完上面的循环后，队列中要么为空，要么值都比当前值大，然后就把当前值添加到队列中
            deque.addLast(nums[i]);
        }
        //窗口区间刚形成后，把队列首位值添加到队列中
        //因为窗口形成后，就需要把队列首位添加到数组中，而下面的循环是直接跳过这一步的，所以需要我们直接添加
        arr[index++] = deque.peekFirst();
        //窗口区间形成
        for (int i = k; i < nums.length; i++) {
            //i-k是已经在区间外了，如果首位等于nums[i-k]，那么说明此时首位值已经不再区间内了，需要删除
            if (deque.peekFirst() == nums[i - k]) deque.removeFirst();
            //删除队列中比当前值大的值
            while (!deque.isEmpty() && nums[i] > deque.peekLast()) deque.removeLast();
            //把当前值添加到队列中
            deque.addLast(nums[i]);
            //把队列的首位值添加到arr数组中
            arr[index++] = deque.peekFirst();
        }
        return arr;
    }

    /**
     * 剑指 Offer 60. n个骰子的点数
     * dp[i][j]  : 骰子个数为i，点数和为j时，有多少种组合；
     * dp[i][j] = dp[i-1][j-1] +  dp[i-1][j - 2] +  dp[i-1][j-3] +  dp[i-1][j-4] +  dp[i-1][j-5] +  dp[i-1][j-6]
     * 当i-1个骰子增加一个骰子的时候，骰子点数分别为1，2，3，4，5，6时的组合个数之和 即为 dp[i][j]
     */
    public double[] dicesProbability(int n) {

        int[][] dp = new int[n + 1][6 * n + 1];
        // 初始化(当一个骰子时候，每种sum 都只有一种组合)
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        // 填充dp[i][j]有多少种组合
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; j++) {  // n为i时候，j的取值范围 ；比如 i=2时候，j 为 2~12
                for (int k = 1; k <= 6; k++) {  //增加一个骰子时，点数会增加1~6 对应的组合数
                    if (j < k) break; //边界处理
                    dp[i][j] += dp[i - 1][j - k];  //累积组合数
                }
            }
        }

        double[] res = new double[5 * n + 1];
        int index = 0;
        double sum = Math.pow(6, n);
        for (int i = n; i <= 6 * n; i++) {
            res[index++] = dp[n][i] / sum;
        }
=======
    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null) {
            return true;
        }

        return f(postorder, 0, postorder.length - 1);
    }

    boolean f(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }

        int root = postorder[j];
        int p = i;
        // 获取第一个大于或者等于 root 等元素的位置
        while (postorder[p] < root) p++;
        // 判断 p ~ j -1 这个范围是否存在小于root的元素
        for (int k = p; k < j; k++) {
            if (postorder[k] < root) {
                return false;
            }
        }

        return f(postorder, i, p - 1) && f(postorder, p, j - 1);
    }


    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     *
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
    public void dfs(TreeNode root, int target) {
        if (root == null) {
            return;
        }

        path.offerLast(root.val);  // 从队列后面增加
        target -= root.val;  // 当前的目标值减去搜索的值
        if (root.left == null && root.right == null && target == 0) {
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
        if (head == null) {
            return null;
        }
        // 复制链表节点
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }

        // 复制随机节点
        cur = head;
        while (cur != null) {
            Node curNew = cur.next;
            curNew.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }

        // 拆分,比如把 A->A1->B->B1->C->C1拆分成 A->B->C和A1->B1->C1
        Node headNew = head.next;
        cur = head;
        Node curNew = head.next;
        while (cur != null) {
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
    public LinkNode treeToDoublyList(LinkNode root) {
        // 将该二叉搜索树转换成一个排序的循环双向链表。

        Queue<LinkNode> queue = new LinkedList<>();
        //中序遍历填充队列
        inorder(root, queue);
        // 给队列中元素链接起来
        LinkNode head = queue.poll();
        LinkNode pre = head;
        while (!queue.isEmpty()) {
            LinkNode cur = queue.poll();
            cur.left = pre;
            pre.right = cur;
            pre = cur;
        }

        pre.right = head;
        head.left = pre;

        return head;
    }

    void inorder(LinkNode root, Queue<LinkNode> queue) {
        if (root == null) {
            return;
        }
        inorder(root.left, queue);
        queue.add(root);
        inorder(root.right, queue);
    }

    //剑指 Offer 37. 序列化二叉树

    /**
     * @param root
     * @return [1, 2, 3, null, null, 4, 5]
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                builder.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                builder.append("null,");
            }
        }
        // TODO 删除末尾所有的 null
        String str = builder.toString();
        String[] split = str.split(",");
        int i = split.length - 1;
        while (split[i].equals("null")) {
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
     *
     * @param data
     * @return
     */
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() <= 0) {
            return null;
        }
        String[] s = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i <= s.length - 1) {
            TreeNode node = queue.poll();
            if (!s[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(s[i]));
                node.left = left;
                queue.add(left);
            }
            i++;

            if (!s[i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(s[i]));
                node.right = right;
                queue.add(right);
            }
            i++;
        }

        return root;
    }

    /**
     * 深度遍历 全排列 剪枝
     *
     * @param s
     * @return
     */
    // 剑指 Offer 38. 字符串的排列

    // 定义一个临时列表
    List<Character> paths;
    // 定义一个结果集
    List<String> result;
    // 定义一个访问集
    boolean[] visited;

    public String[] permutation(String s) {
        paths = new ArrayList<>();
        result = new ArrayList<>();
        visited = new boolean[s.length()];
        char[] arr = s.toCharArray();
        dfs(arr, 0);
        String[] ss = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ss[i] = String.valueOf(result.get(i));
        }
        return ss;
    }

    // 深度遍历, k代表下一位
    void dfs(char[] arr, int k) {
        if (arr.length == k) {  // 满足结果，终止dfs
            result.add(listToString(paths));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            //剪枝操作 (当前的字符和前面的重复了， 就跳过)
            if (i > 0 && arr[i] == arr[i - 1] && !visited[i - 1]) {
                continue;
            }

            if (!visited[i]) {  // 未被访问
                visited[i] = true;
                paths.add(arr[i]);
                dfs(arr, k + 1);
                //回溯
                paths.remove(paths.size() - 1);
                visited[i] = false;
            }
        }

    }

    String listToString(List<Character> paths) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < paths.size(); i++) {
            s.append(paths.get(i));
        }
        return s.toString();
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return new int[0];
        }

        return quickFind(arr, 0, arr.length - 1, k);
    }

    int[] quickFind(int[] arr, int left, int right, int k) {
        int i = partition(arr, left, right);  // 返回 快排后的下标i
        if (i + 1 == k) {  // 在快排的时候，所有的下标都会被遍历。i+1 == k 是必然会出现的。而在 在下标 i+1之前的array 必然是由最小的k个数组成的。
            return Arrays.copyOf(arr, k);
        }
        if (i + 1 > k) {
            return quickFind(arr, 0, i - 1, k);
        } else {
            return quickFind(arr, i + 1, right, k);
        }

    }

    // 找出pivot的下标以及使小于等于pivot在左边，大于等于的在右边  [3,2,5,4,1,6] -> [3,2,1,4,5,6] -> [1,2,3,4,5,6]   (j = 2)
    int partition(int[] arr, int left, int right) {
        int pivot = arr[left];

        int i = left + 1;
        int j = right;

        while (i < j) {
            while (i <= j && arr[i] <= pivot) i++;
            while (i <= j && arr[j] >= pivot) j--;
            if (i >= j) {
                break;
            }

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        arr[left] = arr[j];
        arr[j] = pivot;

        return j;
    }

    // 两个有序数组，合并为一个有序数组 1,3,5   1,2,3,4  注意边界
    public int[] sortArray(int[] a, int[] b) {

        int len_a = a.length;
        int len_b = b.length;
        int len = len_b + len_a;

        int[] res = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (k < len ){
            if (i < a.length && j < b.length) {
                if (a[i] > b[j]) {
                    res[k++] = b[j++];
                } else {
                    res[k++] = a[i++];
                }
            }
            if(i == a.length  && j != b.length ){
                while (j < len_b){
                    res[k++] = b[j++];
                }

            }
            if(j == b.length  && i != a.length ){
                while (i < len_a){
                    res[k++] = a[i++];
                }
            }
        }
        return res;
    }

    // 或者将数组转链表
    public int[] sortArray2(int[] a, int[] b){
        Node node1 = new Node(-1);
        Node node2 = new Node(-1);
        Node node = new Node(-1);

        Node tmp = node1;
        for (int i = 0; i < a.length; i++) {
            Node dumpy = new Node(a[i]);
            tmp.next = dumpy;
            tmp = dumpy;
        }

        Node tmp2 = node2;
        for (int i = 0; i < b.length; i++) {
            Node dumpy = new Node(b[i]);
            tmp2.next = dumpy;
            tmp2 = dumpy;
        }

        Node tmp3 = node;
        node1 = node1.next;
        node2 = node2.next;
        while (node1.next != null && node2.next != null){
            if(node1.val > node2.val){
                tmp3.next = node2;
                tmp3 = tmp3.next;
                node2 = node2.next;
            }else {
                tmp3.next = node1;
                tmp3 = tmp3.next;
                node1 = node1.next;
            }
        }

        while (node1 != null){
            tmp3.next = node1;
            tmp3 = tmp3.next;
            node1 = node1.next;
        }
        while (node2 != null){
            tmp3.next = node2;
            tmp3 = tmp3.next;
            node2 = node2.next;
        }

        int[] res = new int[a.length + b.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = node.next.val;
            node = node.next;
        }

>>>>>>> d6dbacff504c61b743d55d5372052f87ced56d84
        return res;
    }

}


