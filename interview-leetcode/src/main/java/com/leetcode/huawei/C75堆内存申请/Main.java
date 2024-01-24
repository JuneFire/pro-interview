package com.leetcode.huawei.C75堆内存申请;
/*
题目描述有一个总空间为100字节的堆，现要从中新申请一块内存，内存分配原则为：优先紧接着前一块已使用内存，分配空间足够且最接近申请
        大小的空闲内存。
        输入描述
        第1行是1个整数，表示期望申请的内存字节数
        第2到第N行是用空格分割的两个整数，表示当前已分配的内存的情况，每一行表示一块已分配的连续内存空间，每行的第1和第2个整数
        分别表示偏移地址和内存块大小，如：
        0 1
        3 2表示 0 偏移地址开始的 1 个字节和 3 偏移地址开始的 2 个字节已被分配，其余内存空闲。

        输出描述
        若申请成功，输出申请到内存的偏移；
        若申请失败，输出-1
        备注：
        1.若输入信息不合法或无效，则申请失败

        2.若没有足够的空间供分配，则申请失败3.堆内存信息有区域重叠或有非法值等都是无效输入
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*这个问题可以通过模拟内存分配的过程来解决。
        首先，我们需要将已分配的内存块按照偏移地址排序，
        然后遍历每两个相邻的内存块，
        检查它们之间是否有足够的空闲空间来满足申请。
        如果有，我们就选择空闲空间最小且足够的内存块。
        如果遍历完所有的内存块都没有找到满足条件的内存块，那么申请失败*/
public class Main {
/**
 * 读取期望申请的内存字节数。
 * 读取已分配的内存的情况，每一行表示一块已分配的连续内存空间，每行的第1和第2个整数分别表示偏移地址和内存块大小。
 * 将已分配的内存块按照偏移地址排序。
 * 初始化最小空闲空间为无穷大，最小空闲空间的偏移地址为-1。
 * 遍历每两个相邻的内存块，计算它们之间的空闲空间。如果空闲空间大于等于期望申请的内存字节数，并且小于最小空闲空间，那么更新最小空闲空间和最小空闲空间的偏移地址。
 * 如果最小空闲空间的偏移地址为-1，那么输出-1，否则输出最小空闲空间的偏移地址。
 * 输入
 * 0 1
 * 3 2
 * 对应内存块的偏移地址和大小
 * [0,1) [3,5)
 */
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int request = sc.nextInt(); // 读取期望申请的内存字节数。
    List<int[]> memory = new ArrayList<>(); // 读取已分配的内存的情况，每一行表示一块已分配的连续内存空间，每行的第1和第2个整数分别表示偏移地址和内存块大小。
    while (sc.hasNext()) {
        int offset = sc.nextInt(); //偏移地址
        int size = sc.nextInt(); //内存块大小
        memory.add(new int[]{offset, offset + size}); // 将已分配的内存块按照偏移地址排序。
    }
    memory.sort(Comparator.comparingInt(a -> a[0]));  // 将已分配的内存块按照偏移地址排序。
    memory.add(0, new int[]{0, 0}); // 初始化
    memory.add(new int[]{100, 100});
    int minGap = Integer.MAX_VALUE;
    int minOffset = -1;
    for (int i = 1; i < memory.size(); i++) {
        int gap = memory.get(i)[0] - memory.get(i - 1)[1];  //遍历每两个相邻的内存块，计算它们之间的空闲空间
        if (gap >= request && gap < minGap) {  //如果空闲空间大于等于期望申请的内存字节数，并且小于最小空闲空间，那么更新最小空闲空间和最小空闲空间的偏移地址。
            minGap = gap;
            minOffset = memory.get(i - 1)[1];  //最小空闲空间的偏移地址
        }
    }
    System.out.println(minOffset);
}
}
