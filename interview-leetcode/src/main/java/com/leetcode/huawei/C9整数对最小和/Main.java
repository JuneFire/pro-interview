package com.leetcode.huawei.C9整数对最小和;
/*
给定两个整数数组array1、array2,数组元素按升序排列。假设从array1、array2中分别取出一个元素可构成一对元素，现在需要取出k对元素
        并对取出的所有元素求和，计算和的最小值。
        注意：
        两对元素如果对应于array1、array2中的两个下标均相同，则视为同一对元素。
        输入描述
        输入两行数组array1、array2,每行首个数字为数组大小size(0<size <= 100),
        $0< \mathrm{array1[ i] < = 1000}$
        $0< \mathrm{array2[ i] }< = 1000$
        接下来一行为正整数k $0<k<=array1$.size() * array2.size()

        输出描述
        满足要求的最小和
*/
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int[] array1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            array1[i] = sc.nextInt();
        }
        int n2 = sc.nextInt();
        int[] array2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            array2[i] = sc.nextInt();
        }
        int k = sc.nextInt();

        /**
         * 代码首先读取两个数组和k的值。
         * 然后，它创建一个优先队列，队列中的元素是一个数组，
         * 包含两个元素的值和第二个元素在数组2中的下标。队列的排序规则是按照两个元素的和的升序排序。
         * 然后，它将数组1中的前k个元素和数组2中的第一个元素放入队列中。
         * 接下来，它从队列中取出最小的元素对，将它们的和加入到总和中，
         * 然后将下一对元素（如果存在的话）放入队列中。这个过程重复k次。最后，它输出总和。
         */
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
        for (int i = 0; i < n1 && i < k; i++) {
            pq.offer(new int[]{array1[i], array2[0], 0});
        }

        int sum = 0;
        while (k-- > 0) {
            int[] cur = pq.poll();
            sum += cur[0] + cur[1];
            if (cur[2] == n2 - 1) continue;
            pq.offer(new int[]{cur[0], array2[cur[2] + 1], cur[2] + 1});
        }

        System.out.println(sum);
    }
}
