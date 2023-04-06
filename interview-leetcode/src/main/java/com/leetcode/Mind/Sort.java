package com.leetcode.Mind;

import com.leetcode.Structure.ListNode;

import java.util.*;

/**
 * @Author: zkcheng
 * @Date: 2021/06/16/20:55
 * @Description:
 */
public class Sort {

    /**
     * 215. Kth Largest Element in an Array (Medium)
     * 题目描述：找到倒数第 k 个最大的元素的元素。
     */
    //1、排序 ：时间复杂度 O(NlogN)，空间复杂度 O(1)
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    //堆 ：时间复杂度 O(NlogK)，空间复杂度 O(K)。
    public int findKthLargest_2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 小顶堆
        for (int val : nums) {
            pq.add(val);
            if (pq.size() > k)  // 维护堆的大小为 K
                pq.poll();
        }
        return pq.peek();
    }

    //快速选择 ：时间复杂度 O(N)，空间复杂度 O(1)
    public int findKthLargest_3(int[] nums, int k) {
        k = nums.length - k;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                break;
            } else if (j < k) {
                l = j + 1;
            } else {
                h = j - 1;
            }
        }
        return nums[k];
    }

    private int partition(int[] a, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (a[++i] < a[l] && i < h) ;
            while (a[--j] > a[l] && j > l) ;
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, l, j);
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 桶排序
     * 1.出现频率最多的 k 个元素
     * 347. Top K Frequent Elements (Medium)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyForNum = new HashMap<>();
        for (int num : nums) {
            frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int key : frequencyForNum.keySet()) {
            int frequency = frequencyForNum.get(key);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }
        List<Integer> topK = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
            if (buckets[i] == null) {
                continue;
            }
            if (buckets[i].size() <= (k - topK.size())) {
                topK.addAll(buckets[i]);
            } else {
                topK.addAll(buckets[i].subList(0, k - topK.size()));
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = topK.get(i);
        }
        return res;
    }

    /**
     * 2. 按照字符出现次数对字符串排序
     * 451. Sort Characters By Frequency (Medium)
     */
    public String frequencySort(String s) {
        Map<Character, Integer> frequencyForNum = new HashMap<>();
        for (char c : s.toCharArray())
            frequencyForNum.put(c, frequencyForNum.getOrDefault(c, 0) + 1);

        List<Character>[] frequencyBucket = new ArrayList[s.length() + 1];
        for (char c : frequencyForNum.keySet()) {
            int f = frequencyForNum.get(c);
            if (frequencyBucket[f] == null) {
                frequencyBucket[f] = new ArrayList<>();
            }
            frequencyBucket[f].add(c);
        }
        StringBuilder str = new StringBuilder();
        for (int i = frequencyBucket.length - 1; i >= 0; i--) {
            if (frequencyBucket[i] == null) {
                continue;
            }
            for (char c : frequencyBucket[i]) {
                for (int j = 0; j < i; j++) {
                    str.append(c);
                }
            }
        }
        return str.toString();
    }

    /**
     * 荷兰国旗问题
     * 荷兰国旗包含三种颜色：红、白、蓝。
     *
     * 有三种颜色的球，算法的目标是将这三种球按颜色顺序正确地排列。它其实是三向切分快速排序的一种变种，在三向切分快速排序中，每次切分都将数组分成三个区间：小于切分元素、等于切分元素、大于切分元素，而该算法是将数组分成三个区间：等于红色、等于白色、等于蓝色。
     *
     * 1. 按颜色进行排序
     * 75. Sort Colors (Medium)
     *
     * 这道题我掌握不到精髓
     */
    public void sortColors(int[] nums) {
        int zero = -1, one = 0, two = nums.length;
        while (one < two) {
            if (nums[one] == 0) {
                swap2(nums, ++zero, one++);
            } else if (nums[one] == 2) {
                swap2(nums, --two, one);
            } else {
                ++one;
            }
        }
    }

    public void sortColors_2(int[] nums) {
        int zero_count = 0;
        int one_count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zero_count++;
            }
            if (nums[i] == 1) {
                one_count++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (zero_count > 0) {
                nums[i] = 0;
                zero_count--;
            } else if (one_count > 0) {
                nums[i] = 1;
                one_count--;
            } else {
                nums[i] = 2;
            }
        }
    }

    // 链表快排
    public static ListNode quickSort(ListNode begin, ListNode end) {
        //判断为空，判断是不是只有一个节点
        if (begin == null || end == null || begin == end)
            return begin;
        //从第一个节点和第一个节点的后面一个几点
        //begin指向的是当前遍历到的最后一个<= nMidValue的节点
        ListNode first = begin;
        ListNode second = begin.next;

        int nMidValue = begin.val;
        //结束条件，second到最后了
        while (second != end.next && second != null) {//结束条件
            //一直往后寻找<=nMidValue的节点，然后与fir的后继节点交换
            if (second.val < nMidValue) {
                first = first.next;
                //判断一下，避免后面的数比第一个数小，不用换的局面
                if (first != second) {
                    int temp = first.val;
                    first.val = second.val;
                    second.val = temp;
                }
            }
            second = second.next;
        }
        //判断，有些情况是不用换的，提升性能
        if (begin != first) {
            int temp = begin.val;
            begin.val = first.val;
            first.val = temp;
        }
        //前部分递归
        quickSort(begin, first);
        //后部分递归
        quickSort(first.next, end);
        return begin;
    }

    private void swap2(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

}
