package com.leetcode.Mind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: zkcheng
 * @Date: 2021/06/15/20:34
 * @Description: Leetcode 题解 - 双指针
 */
public class DoublePoint {

    /**
     * 题目描述：在有序数组中找出两个数，使它们的和为 target。
     * 如果两个指针指向元素的和 sum == target，那么得到要求的结果；
     * 如果 sum > target，移动较大的元素，使 sum 变小一些；
     * 如果 sum < target，移动较小的元素，使 sum 变大一些。
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) return null;
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    /**
     * 633. Sum of Square Numbers (Easy)
     * 题目描述：判断一个非负整数是否为两个整数的平方和。
     *
     * 可以看成是在元素为 0~target 的有序数组中查找两个数，使得这两个数的平方和为 target，如果能找到，则返回 true，表示 target 是两个整数的平方和。
     * @param target
     * @return
     */
    public boolean judgeSquareSum(int target) {
        if (target < 0) return false;
        int i = 0, j = (int) Math.sqrt(target);
        while (i <= j) {
            int powSum = i * i + j * j;
            if (powSum == target) {
                return true;
            } else if (powSum > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    /**
     * 345. Reverse Vowels of a String (Easy)
     *
     * 使用双指针，一个指针从头向尾遍历，一个指针从尾到头遍历，当两个指针都遍历到元音字符时，交换这两个元音字符。
     */
    private final static HashSet<Character> vowels = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public String reverseVowels(String s) {
        if (s == null) return null;
        int i = 0, j = s.length() - 1;
        char[] result = new char[s.length()];
        while (i <= j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!vowels.contains(ci)) {
                result[i++] = ci;
            } else if (!vowels.contains(cj)) {
                result[j--] = cj;
            } else {
                result[i++] = cj;
                result[j--] = ci;
            }
        }
        return new String(result);
    }

    /**
     * 680. Valid Palindrome II (Easy)
     *
     * 题目描述：可以删除一个字符，判断是否能构成回文字符串。
     *
     * 在试着删除字符时，我们既可以删除左指针指向的字符，也可以删除右指针指向的字符。
     */

    public boolean validPalindrome(String s){
        for (int i = 0, j = s.length() - 1; i < j;  i++, j--) {
            if(s.charAt(i) != s.charAt(j)){
                return isPalindrome(s, i, j-1) || isPalindrome(s , i - 1, j);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 88. Merge Sorted Array (Easy)
     * 题目描述：把归并结果存到第一个数组上。
     *
     * 需要从尾开始遍历，否则在 nums1 上归并得到的值会覆盖还未进行归并比较的值。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n){
        int index1 = m - 1, index2 = n - 1 ;
        int indexMerge = m + n - 1;
        while (index2 >= 0){
            if(index1 < 0){ // index1 一直大于 index2 (lenth小于index2)
                nums1[indexMerge--] = nums2[index2--];
            }
            else if(nums1[index1] > nums2[index2]){
                nums1[indexMerge--] = nums1[index1--];
            }else {
                nums1[indexMerge--] = nums2[index2--];
            }
        }
    }

    /**
     * 判断链表是否存在环
     * 141. Linked List Cycle (Easy)
     *
     * 使用双指针，一个指针每次移动一个节点，一个指针每次移动两个节点，如果存在环，那么这两个指针一定会相遇。
     */
    public boolean hasCycle(ListNode head){
        if (head == null)
            return false;
        ListNode node1 = head, node2 = head.next;
        while (node1 != null && node2 != null && node2.next != null){
            if(node1 == node2)
                return true;
            node1 = node1.next;
            node2 = node2.next.next;
        }
        return false;
    }

    /**
     * 最长子序列
     * 524. Longest Word in Dictionary through Deleting (Medium)
     *
     * 题目描述：删除 s 中的一些字符，使得它构成字符串列表 d 中的一个字符串，找出能构成的最长字符串。如果有多个相同长度的结果，返回字典序的最小字符串。
     */

    public String findLongestWord(String s, List<String> d){
        String longestWord = "";
        for(String target : d){
            int l1 = longestWord.length(), l2 = target.length();
            if(l1 > l2 || (l1 == l2 && longestWord.compareTo(target) < 0)){
                continue;
            }
            if(isSubStr(s, target)){
                longestWord = target;
            }
        }
        return longestWord;
    }

    /**
     * 判断target是否是s的条件子串
     * @param s
     * @param target
     * @return
     */
    private boolean isSubStr(String s, String target){
        int i = 0, j  = 0;
        while (i < s.length() && j < target.length()){
            if(s.charAt(i) == target.charAt(j)){
                j++;
            }
            i++;
        }
        return j == target.length();
    }


    public static void main(String[] args){
            DoublePoint doublePoint = new DoublePoint();
            String[] test = new String[]{"ale","apple","monkey","plea"};
            System.out.println(doublePoint.findLongestWord("abpcplea", Arrays.asList(test)));
    }



}

class ListNode {
    int val;
    ListNode next;
    ListNode(int index){
        val = index;
        next = null;
    }
}