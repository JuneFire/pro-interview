package com.leetcode.huawei.C6查找众数及中位数;
/*

众数是指一组数据中出现次数量多的那个数，众数可以是多个。中位数是指把一组数据从小到大排列，最中间的那个数，如果这组数据的个数是奇数，那最中间那个就是中位数，如果这组数据的个数为
        偶数，那就把中间的两个数之和除以2，所得的结果就是中位数。
        查找整型数组中元素的众数并组成一个新的数组，求新数组的中位数。
        输入描述
        输入一个一维整型数组，数组大小取值范围 0<N<1000,数组中每个元素取值范围 0<E<1000
        输出描述
        输出众数组成的新数组的中位数
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 4, 4, 5, 5, 6, 7};
        int[] modeArray = findModeArray(array);
        double median = findMedian(modeArray);
        System.out.println("Mode Array: " + Arrays.toString(modeArray));
        System.out.println("Median: " + median);
    }

    public static int[] findModeArray(int[] array) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int maxFrequency = 0;

        for (int num : array) {
            int frequency = frequencyMap.getOrDefault(num, 0) + 1;
            frequencyMap.put(num, frequency);
            maxFrequency = Math.max(maxFrequency, frequency);
        }

        List<Integer> modeList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modeList.add(entry.getKey());
            }
        }

        int[] modeArray = new int[modeList.size()];
        for (int i = 0; i < modeList.size(); i++) {
            modeArray[i] = modeList.get(i);
        }

        Arrays.sort(modeArray);
        return modeArray;
    }

    public static double findMedian(int[] array) {
        int length = array.length;
        if (length % 2 == 0) {
            int midIndex1 = length / 2 - 1;
            int midIndex2 = length / 2;
            return (array[midIndex1] + array[midIndex2]) / 2.0;
        } else {
            int midIndex = length / 2;
            return array[midIndex];
        }
    }
}
