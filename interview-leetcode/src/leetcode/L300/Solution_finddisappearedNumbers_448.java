package leetcode.L300;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zkcheng
 * @Date: 2022/06/16/16:36
 * @Description:
 */
public class Solution_finddisappearedNumbers_448 {

    /**
     * 找到包含n个整数的nums[i](在区间 1-n)中消失的数字
     *
     * 思路：(概念上把对应的数字，放在对应的下标位置上)
     *       既然是长度为n且区间为1-n的数组，自然可以假定大家都在对应的下标位置上。而那些消失的数字，则为下标+1。
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums){
            int x = (num - 1) % n;  // 还原num本来的值  // 其实这里可以换种其它标记方法
            nums[x] += n;     // 对出现过的数字进行标记
        }
        // 找到未被标记的数字的下标
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if(nums[i] <= n){
                result.add(i + 1);
            }
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(2 % 2);
    }
}
