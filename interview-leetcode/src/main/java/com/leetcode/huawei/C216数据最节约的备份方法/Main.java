package com.leetcode.huawei.C216数据最节约的备份方法;

/*题目描述：数据最节约的备份方法有若干个文件，使用刻录光盘的方式进行备份，假设每张光盘的容量是500MB，求使用光盘最少的文件分布方式
        所有文件的大小都是整数的MB，且不超过500MB；文件不能分割、分卷打包
        输入描述：
        一组文件大小的数据
        输出描述：
        使用光盘的数量
        不用考虑输入数据不合法的情况；假设最多100个输入文件。
        用例1
        输入：
        $1\mid100$,500,300,200,400输出：

        $1\mid3$*/
import java.util.*;

/**
 * 这个问题可以使用贪心算法来解决。我们可以先将所有的文件按照大小进行排序，然后从最大的文件开始，尽可能地将剩余空间填满
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(",");
        int[] files = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            files[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(files);
        int left = 0, right = files.length - 1;
        int count = 0;
        while (left <= right) {
            if (files[left] + files[right] <= 500) {
                left++;
            }
            right--;
            count++;
        }
        System.out.println(count);
    }
}
