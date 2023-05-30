package com.leetcode.offer;

import com.leetcode.Structure.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zkCheng
 * @date 2023/5/19 15:44
 */
class Solution {

    /**
     * 92. 反转链表 II   (局部头插法)
     * https://leetcode.cn/problems/reverse-linked-list-ii/description/
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null) return null;
        // 局部头插法
        ListNode dumpy = new ListNode(0);
        dumpy.next = head;
        ListNode p = dumpy; //找到left 位置
        for(int i = 1; i < left; i++){
            p = p.next;
        }

        int count = right - left;
        // 两个指针，一个指向1（p），一个指向2（cur）
        ListNode cur = p.next; //从node1位置开始头插
        // 不断的移动cur，将cur头插p
        for(int i = 0; i < count; i++){

            ListNode insertNode = cur.next;  // 要插的节点
            cur.next = cur.next.next;  // 移动cur （3，4）

            ListNode headNode = p.next;  // 将该节点插入到p 和 p.next 之间，实现头插入
            p.next = insertNode;
            insertNode.next = headNode;
        }

        return dumpy.next;
    }

    List path;
    List res;
    boolean[] visited;
    public String[] permutation(String s) {
        this.path = new ArrayList<>();
        this.res = new ArrayList<>();
        this.visited = new boolean[s.length()];

        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        dfs(arr, 0);

        String[] ss = new String[res.size()];
        for(int i = 0; i < res.size(); i++){
            ss[i] = (String) res.get(i);
        }

        return ss;

    }
    // 时间复杂度：O(n*n!) 空间：N,N,递归调用最大深度也是 N，3n,O(n)
    void dfs(char[] arr, int k){
        if(arr.length == k){
            res.add(listToString(path));
            return;
        }

        //进行N叉树搜索  这里的主要思想主要还是对这个visited进行全排列
        for(int i = 0; i < arr.length; i++){
            // 剪枝 aab
            if(i > 0 && arr[i] == arr[i - 1] && visited[i - 1] == false){
                continue;
            }
            if(visited[i] == false){
                // 递归访问
                visited[i] = true;
                path.add(arr[i]);
                dfs(arr, k + 1);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }

    String listToString(List list){
        StringBuilder b = new StringBuilder();
        for(int i = 0; i < list.size(); i++){
            b.append(list.get(i));
        }

        return b.toString();
    }


    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.permutation("aabc"));
    }
}

