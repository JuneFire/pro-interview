package leetcode.L300;

import java.util.Arrays;

/**
 * @Author: zkcheng
 * @Date: 2022/07/13/11:58
 * @Description:
 */
public class Sort {


    // 快速排序
    public static void quickSort(int[] data) {
        subSort(data, 0, data.length - 1);
    }

    public static void subSort(int[] nums, int start, int end) {
        if (start < end) {
            int base = nums[start];
            int low = start;
            int high = end;
            while (true) {
                while (low < end && nums[low] <= base)
                    low++;
                while (high > start && nums[high] >= base)
                    high--;
                if (low < high) {
                    swap(nums, low, high);
                } else {
                    break;
                }
            }
            swap(nums, start, high);

            subSort(nums, start, high - 1);
            subSort(nums, high + 1, end);
        }
    }


    //希尔排序
    public static int[] ShellSort(int[] nums){
        int len = nums.length;
        int currentValue, gap = len/2;
        while (gap > 0){
            for (int i =+ gap; i < len; i++){
                currentValue = nums[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && nums[preIndex] > currentValue){  // 每隔gap段，比较一下
                    nums[preIndex + gap] = nums[preIndex];
                    preIndex -= gap;
                }
                nums[preIndex + gap] = currentValue;
            }
            System.out.println("本轮增量【"+ gap + "】排序后的数组");
            System.out.println(Arrays.toString(nums));;
            gap /= 2;
        }
        return nums;
    }

    public static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static void main(String[] args) {
//        int[] data = {9, -16, 30, 23, -30, -49, 25, 21, 30};
//        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
//        quickSort(data);
//        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
        int[] data = {86, 39, 77, 23, 32, 45, 58, 63, 93, 4, 37, 22};
        ShellSort(data);
    }
}
