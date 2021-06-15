package leetcode.Mind;

import java.util.Arrays;
import java.util.HashSet;

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


    public static void main(String[] args){
            DoublePoint doublePoint = new DoublePoint();

    }



}
