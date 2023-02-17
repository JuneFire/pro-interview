package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: zkcheng
 * @Date: 2022/08/06/10:06
 * @Description:
 */
public class Solution1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while (n-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(MaxPackageNum(a, b));
        }
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] a = new int[n];
//        int[] b = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            a[i] = sc.nextInt();
//        }
//        for (int i = 0; i < n; i++) {
//            b[i] = sc.nextInt();
//        }
//        System.out.println(magicChange(a , b));
//    }


    // 小美的数据分析
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int a = sc.nextInt();
//        int b = sc.nextInt();
//        int[] nums = new int[a];
//        for (int i = 0; i < a; i++) {
//            nums[i] = sc.nextInt();
//
//        }
//
//        System.out.println(nums);
//    }

    public static void printData(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
    }



    // 魔法阵
    public static int magicChange(int[] a, int[] b) {
        if (a == null || b == null) {
            return -1;
        }
        if (a.length != b.length) {
            return -1;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0 || b[i] < 0) {
                return -1;
            }
        }
        if (checkNums(a)) {
            return 0;
        }
        //  翻滚次数
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) {
                continue;
            } else {
                a[i] = b[i];
                count++;
                if (checkNums(a)) {
                    return count;
                }
            }
        }
        return -1;
    }

    private static boolean checkNums(int[] a) {
        int len = a.length;
        int half = len / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : a) {
            map.putIfAbsent(n, 1);
        }
        for (int n : a) {
            int count = map.get(n);
            if (count > half) {
                return true;
            } else {
                map.put(n, ++count);
            }
        }
        return false;
    }


    public static int MaxPackageNum(int a, int b) {
        int min = Math.min(a, b);  // 找出最大的值
        if (min == 0) {
            return 0;
        }
        int sum = a + b;
        if (sum < 3) {
            return 0;
        }

        int res = 0;

        for (int i = 1; i <= min; ++i) {
            if (3 * i == sum) {
                return i;
            } else if (3 * i < sum && (sum - 3 * i >= 2)) {
                res = i;
            }else {
                res = i;
                break;
            }
        }
        return res;
    }

}
