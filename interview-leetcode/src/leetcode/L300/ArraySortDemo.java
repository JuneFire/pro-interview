package leetcode.L300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zkCheng
 * @date 2022/7/19 9:58
 */
public class ArraySortDemo {

    /**
     * 数组交换函数
     * @param array
     * @param i
     * @param j
     */
    public void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int len = 0; //数组长度

    // 进行堆排序
    public int[] sortArray(int[] nums){
        len = nums.length;
        if(len <= 1) return nums;
        // 构建一个最大堆
        buildMaxHeap(nums);
        // 循环将堆首位（最大值）与未排序数据末位交换，然后重新调整为最大堆
        while (len > 0){
            swap(nums, 0 , len - 1);
            len--;
            adjustHeap(nums, 0);

            System.out.println( Arrays.toString(nums));
            System.out.println( "---------------------");
        }
        return nums;
    }

    //建立最大堆
    public void buildMaxHeap(int[] array){
        /*从最后一个非叶子节点开始向上构造最大堆*/
        for (int i = array.length / 2 - 1; i >= 0; i--){
            // 调整
            adjustHeap(array, i);
        }
        System.out.println("最大堆：" + Arrays.toString(array));
    }

    // 调整堆为最大堆
    public void adjustHeap(int[] nums, int i){
        int maxInt = i;
        int left = 2 * i + 1;  // 左节点
        int right = 2 * (i + 1); // 右节点
        if(left < len && nums[left] > nums[maxInt])  // 在可比较的区域内进行比较，左节点下标不能大于一直递减的数组长度
            maxInt = left;
        if(right < len && nums[right] > nums[maxInt] && nums[right] > nums[left])
            maxInt = right;
        // 如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换位置
        if(maxInt != i){
            swap(nums, maxInt, i);
            System.out.println(Arrays.toString(nums));
            adjustHeap(nums, maxInt);
        }
    }


    /**
     * 基数排序
     */
    public int[] RadixSort(int[] nums){
        if(nums == null || nums.length < 2){
            return nums;
        }
        // 找出最大值
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if(max < nums[i]){
                max = nums[i];
            }
        }

        // 计算它的最大位数，决定我们即将几轮排序
        int maxDigt = 0;
        while (max != 0){
            max /= 10;
            maxDigt++;
        }
        int mod = 10, div = 1;
        // 构建桶子
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < 10; i++) 
            bucketList.add(new ArrayList<>());
        // 遍历入桶
        for (int i = 0; i < maxDigt; i++, mod *= 10, div *= 10) {
            System.out.println("第"+ i + "轮排序");
            // 遍历原始数组，入桶
            for (int j = 0; j < nums.length; j++) {
                int num = (nums[j] % mod) / div;
                bucketList.get(num).add(nums[j]);
            }

            // 看看桶子内部情况
            for (int j = 0; j < bucketList.size(); j++) {
                System.out.print("第" + j + "个桶子:");
                for(Integer val : bucketList.get(j)){
                    System.out.print(val + " ");
                }
                System.out.println();
            }

            // 桶子中的元素填回原来数组
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++) {
                    nums[index++] = bucketList.get(j).get(k);
                }
                bucketList.get(j).clear();
            }
            System.out.println("第" + i + "轮的数组情况");
            System.out.println(Arrays.toString(nums));
        }
        return nums;
    }

    public static void main(String[] args){
        ArraySortDemo demo = new ArraySortDemo();
        int[] nums = new int[]{35, 63, 48, 9, 86, 24, 53, 13, 12,16, 0};
//        demo.sortArray(nums);
        demo.RadixSort(nums);
    }
}
