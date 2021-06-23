package leetcode.Mind;

import leetcode.Structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zkcheng
 * @Date: 2021/06/23/20:58
 * @Description:
 */
public class DivideConquer {
    /**
     * 给表达式加括号
     * 241. Different Ways to Add Parentheses (Medium)
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '+' || c == '-' || c == '*'){
                List<Integer> leftList = diffWaysToCompute(input.substring(0,i));
                List<Integer> rightList = diffWaysToCompute(input.substring(i + 1));
                for(int l : leftList){
                    for (int r : rightList){
                        if(c == '+'){
                            ways.add(l + r);
                        }else if(c == '-'){
                            ways.add(l - r);
                        }else {
                            ways.add(l * r);
                        }
                    }
                }

            }
        }
        if (ways.size() == 0){
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }

    /**
     * 95. Unique Binary Search Trees II (Medium) 不同的二叉搜索树 II
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     */
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new LinkedList<TreeNode>();
        }
        return generateSubtrees(1, n);
    }

    private List<TreeNode> generateSubtrees(int s, int e) {
        List<TreeNode> res = new LinkedList<TreeNode>();
        if (s > e) {
            res.add(null);
            return res;
        }
        for (int i = s; i <= e; ++i) {
            List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);
            List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }

}
