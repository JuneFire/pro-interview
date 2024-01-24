package com.leetcode.offer;

/**
 * @author zkCheng
 * @date 2023/5/18 13:18
 */
public class LinkNode {
    public int val;
    public LinkNode left;
    public LinkNode right;

    public LinkNode() {}

    public LinkNode(int _val) {
        val = _val;
    }

    public LinkNode(int _val,LinkNode _left,LinkNode _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
