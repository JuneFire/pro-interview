package com.leetcode.offer;

import com.leetcode.Structure.TreeNode;

import java.util.*;

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
        return res;
    }

}
